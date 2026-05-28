package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@Slf4j
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {

    @Resource
    private ISeckillVoucherService seckillVoucherService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedissonClient redissonClient;

    private static final DefaultRedisScript<Long> SECKILL_SCRIPT; //创建脚本对象
    //静态代码块配置脚本对象
    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua")); //直接在类当前目录下面找
        SECKILL_SCRIPT.setResultType(Long.class);
    }


    private static final ExecutorService SECKILL_ORDER_EXECUTOR =  Executors.newSingleThreadExecutor(); //创建单线程

    @PostConstruct //作用:在这个类加载后马上执行
    private void init(){
        SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler());
    }

    //子线程，处理消息队列中的消息
    private class VoucherOrderHandler implements Runnable{
        String queueName = "stream.orders";
        @Override
        public void run() {
            while(true){
                try {
                    //1. 获取队列中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 BLOCK 2000 STREAMS streams.order >
                    List<MapRecord<String,Object,Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(queueName, ReadOffset.lastConsumed())
                    );
                    //2. 判断消息获取是否成功
                    if(list == null || list.isEmpty()){
                        //如果获取失败，说明没有消息，继续下一次循环
                        continue;
                    }

                    //3. 解析消息中的订单信息
                    MapRecord<String,Object,Object> record = list.get(0); //String是消息的Id，
                    Map<Object,Object> value = record.getValue(); //拿到键值对
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(),true);
                    //4. 如果获取成功，可以下单
                    handlerVoucherOrder(voucherOrder);
                    //5.ACK确认 SACK stream.orders g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName, "g1", record.getId());
                } catch (Exception e) {
                    log.error("处理订单异常",e);
                    handPendingList();
                }
            }
        }

        private void handPendingList() {
            while(true){
                try {
                    //1. 获取pending-list中的订单信息 XREADGROUP GROUP g1 c1 COUNT 1 STREAMS streams.order 0
                    List<MapRecord<String,Object,Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1),
                            StreamOffset.create(queueName, ReadOffset.from("0"))
                    );
                    //2. 判断消息获取是否成功
                    if(list == null || list.isEmpty()){
                        //如果获取失败，说明pending-list中没有异常消息，结束
                        break;
                    }

                    //3. 解析消息中的订单信息
                    MapRecord<String,Object,Object> record = list.get(0); //String是消息的Id，
                    Map<Object,Object> value = record.getValue(); //拿到键值对
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(value, new VoucherOrder(),true);
                    //4. 如果获取成功，可以下单
                    handlerVoucherOrder(voucherOrder);
                    //5.ACK确认 SACK stream.orders g1 id
                    stringRedisTemplate.opsForStream().acknowledge(queueName, "g1", record.getId());
                } catch (Exception e) {
                    log.error("处理pending-list订单异常",e);
                }
            }
        }
    }

//    private BlockingQueue<VoucherOrder> orderTasks = new ArrayBlockingQueue<>(1024 * 1024);
//    private class VoucherOrderHandler implements Runnable{
//
//        @Override
//        public void run() {
//             while(true){
//                 try {
//                     //1. 获取队列中的订单信息
//                     VoucherOrder voucherOrder = orderTasks.take(); //从队列中拿出
//                     //2. 创建订单
//                     handlerVoucherOrder(voucherOrder);
//                 } catch (Exception e) {
//                     log.error("处理订单异常",e);
//                 }
//             }
//        }
//    }

    //基于Redis完成秒杀资格判断 (主线程)
    private void handlerVoucherOrder(VoucherOrder voucherOrder) {
        //1. 获取用户id
        Long userId = voucherOrder.getUserId();
        //2. 创建锁对象
        // SimpleRedisLock lock = new SimpleRedisLock("order" + userId, stringRedisTemplate );
        RLock lock = redissonClient.getLock("lock:order:" + userId);
        //3. 判断锁是否获取成功
        boolean isLock = lock.tryLock();
        if(!isLock){
            // 获取锁失败，返回错误信息
            log.error("不允许重复下单");
            return ;
        }

        try {
//            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();//获取代理对象(事务)
            proxy.createVoucherOrder(voucherOrder); //自我调用，会导致事务失效，获取代理对象，调用代理对象的方法，
        } finally {
            lock.unlock();
        }
    }

    private IVoucherOrderService proxy;

    @Override
    public Result seckillVoucher(Long voucherId) {
        //获取用户
        Long userId = UserHolder.getUser().getId();
        //订单Id
        long orderId = redisIdWorker.nextId("order");
        //1. 调用lua脚本
        Long result =  stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(),userId.toString(),String.valueOf(orderId)
        );
        //2. 判断结果是否为0
        int r = result.intValue();
        if(r != 0){
            //2.1 不为0，没有购买资格
            return  Result.fail(r == 1 ? "库存不足" : "不能重复下单");
        }

        //3. 获取代理对象
        proxy = (IVoucherOrderService) AopContext.currentProxy();//获取代理对象(事务)
        //4. 返回订单id
        return Result.ok(orderId);

    }

//    @Override
//    public Result seckillVoucher(Long voucherId) {
//
//        //1. 查询秒杀优惠券
//        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
//        //2. 判断秒杀是否开始
//        if(voucher.getBeginTime().isAfter(LocalDateTime.now())){
//            return Result.fail("秒杀尚未开始");
//        }
//
//        //3. 判断秒杀是否结束
//        if(voucher.getEndTime().isBefore(LocalDateTime.now())) {
//            return Result.fail("秒杀已经结束");
//        }
//
//        //4. 判断库存是否充足
//        if(voucher.getStock()< 1){
//            return Result.fail("库存不足");
//        }
//
////        Long userId = UserHolder.getUser().getId();
////        synchronized (userId.toString().intern()) { //因为userId是包装类对象，所以要转成字符串，给锁一样的string值
////            // 然而返回的是引用，所以要加intern()，返回字符串的规范表示，如果有相同的字符串，就返回相同的引用
////            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();//获取代理对象(事务)
////            return proxy.createVoucherOrder(voucherId); //自我调用，会导致事务失效，获取代理对象，调用代理对象的方法，
////        }
//
//        Long userId = UserHolder.getUser().getId();
//        //创建锁对象
//        // SimpleRedisLock lock = new SimpleRedisLock("order" + userId, stringRedisTemplate );
//        RLock lock = redissonClient.getLock("lock:order:" + userId);
//
//        boolean isLock = lock.tryLock();
//        if(!isLock){
//            // 获取锁失败，返回错误信息
//            return Result.fail(" 不允许重复下单");
//        }
//
//        try {
//            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();//获取代理对象(事务)
//            return proxy.createVoucherOrder(voucherId); //自我调用，会导致事务失效，获取代理对象，调用代理对象的方法，
//        } finally {
//            lock.unlock();
//        }
//    }

    @Transactional
    public void createVoucherOrder(VoucherOrder voucherOrder) { //在方法上加锁，太降低效率了，在同一个用户上加锁就行

        //5. 一人一单
        Long userId = voucherOrder.getUserId();

//        synchronized (userId.toString().intern()) { //因为userId是包装类对象，所以要转成字符串，给锁一样的string值
//                                                    // 然而返回的是引用，所以要加intern()，返回字符串的规范表示，如果有相同的字符串，就返回相同的引用
            //5.1 查询订单
            int count = query().eq("user_id", userId).eq("voucher_id", voucherOrder).count();
            //5.2 判断是否存在
            if (count > 0) {
                log.error("用户已经购买过一次了");//因为在redis中判断了，在这里不太可能会出错了
                return ;
            }

            //6. 扣减库存
            boolean success = seckillVoucherService.update()
                    .setSql("stock = stock - 1") //自定义sql使库存减一
                    .eq("voucher_id", voucherOrder.getVoucherId()).gt("stock", 0)// where id = ? and stock > 0
                    .update();


            if (!success) {
                return ;
            }


            //7. 创建订单
            save(voucherOrder);//保存订单信息
    }
}
