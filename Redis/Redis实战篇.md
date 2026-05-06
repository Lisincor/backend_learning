# 短信登录

## 0.

![屏幕截图(1372)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1372).png)



登陆校验所有功能代码再这5个page中

![image-20251029155732452](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251029155732452.png)

## 1.实现登录校验拦截器



## 2. 基于redis实现共享session登录

流程图：看看得了

![image-20251029163732317](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251029163732317.png)

**前后端交互**

![image-20251111131459818](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251111131459818.png)



具体看这六个代码，梳理流程，核心就是看代码：

![屏幕截图(1380)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1380).png)



### 拦截器优化

![image-20251110200340286](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110200340286.png)

![image-20251103160745179](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251103160745179.png)



依旧看代码：

![image-20251103160848518](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251103160848518.png)



# 商户查询缓存

## 1. 概览

![image-20251103163902987](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251103163902987.png)


## 2.  添加商户缓存

```java
package com.hmdp.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.Shop;
import com.hmdp.mapper.ShopMapper;
import com.hmdp.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.hmdp.utils.RedisConstants.CACHE_SHOP_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryById(Long id) {
        String key = CACHE_SHOP_KEY + id;
        //1.从redis查询缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) {
            //3.如果存在，直接返回
            // 将json字符串数据，转换为Shop对象
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return Result.ok(shop);
        }

        //4.如果不存在，查询数据库
        Shop shop = getById(id); //mybatis-plus

        //5.不存在，返回错误
        if (shop == null) {
            return Result.fail("店铺不存在");
        }

        //6.存在，写入redis缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop));

        //7.返回结果
        return Result.ok(shop);
    
    }
}

```



## 3.添加缓存练习（重点）

具体看代码：有三种方式：String，List，Zset，这三种代表着第一步从redis查询缓存时查询出来和最后一步存入redis的数据类型

 1.写入redis缓存时，都是转换成Json字符串写入

2.  返回的响应结果要的数据是List集合类型，如果是String就要tolist，List就直接返回，set就遍历set存到一个list中

、![屏幕截图(1386)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1386).png)



## 4.缓存更新策略（重点）



![image-20251104104001934](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104104001934.png)

主动更新策略

![image-20251104104031992](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104104031992.png)

第一种策略：

![image-20251104105122540](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104105122540.png)

先操作数据库再删除缓存，对于线程安全问题的可能性会最低

![屏幕截图(1391)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1391).png)



## 5.实现商铺缓存与数据库的双写模式

![image-20251104142359224](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104142359224.png)

**先操作数据库，再删除缓存**

```java
    
controller:

	@PutMapping
    public Result updateShop(@RequestBody Shop shop) {

        return shopService.update(shop);

    }

service:
    @Override
    @Transactional
    public Result update(Shop shop) {
        Long id = shop.getId();
        if(id == null ){
            return Result.fail("店铺id不能为空");
        }
        //1.更新数据库
        updateById(shop);
        //2.删除缓存
        stringRedisTemplate.delete(CACHE_SHOP_KEY + id); //如果抛异常，那么将要进行回滚
        //3.返回结果
        return Result.ok();
    }

   
```



## 6.缓存穿透



![image-20251104143404480](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104143404480.png)

最后还是采用第一种方式

## 7.编码解决商铺查询缓存穿透问题

方案：缓存null值

![image-20251104150217794](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104150217794.png)

```java
public Shop queryWithMutex(Long id) {
        String key = CACHE_SHOP_KEY + id;
        //1.从redis查询缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) { //如果是空值，就会往下走
            //3.如果存在，直接返回
            // 将json字符串数据，转换为Shop对象
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }
        //判断命中的是否是空值，因为有空值所以有redis对象，所以不是null
        if(shopJson != null){
            return null;
        }

        //4.如果不存在，查询数据库
        Shop shop = getById(id); //mybatis-plus

        //5.不存在，返回错误
        if (shop == null) {

            //将空值写入redis，防止缓存穿透
            stringRedisTemplate.opsForValue().set(key, "",CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }

        //6.存在，写入redis缓存
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);

        //7.返回结果
        return shop;
    }
```

总结

![image-20251104152533192](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104152533192.png)



## 8.缓存雪崩

![image-20251104153829306](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104153829306.png)



## 9.缓存击穿问题及解决方案

**缓存击穿问题**也叫热点Key问题，就是一个被**高并发访问**并且**缓存重建业务比较复杂**的key突然失效了，无法在缓存中查到，然后就会往下查数据库，无数的请求访问会给数据库带来巨大的冲击。

![屏幕截图(1400)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1400).png)



![image-20251104165521548](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104165521548.png)



## 10.利用互斥锁解决缓存击穿问题

![image-20251104190453950](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104190453950.png)

看这个板块的的代码：

![image-20251104191000498](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251104191000498.png)

```java
public Shop queryWithMutex(Long id) {
        String key = CACHE_SHOP_KEY + id;
        //1.从redis查询缓存
        String shopJson = stringRedisTemplate.opsForValue().get(key);
        //2.判断是否存在
        if (StrUtil.isNotBlank(shopJson)) { //如果是空值，就会往下走
            //3.如果存在，直接返回
            // 将json字符串数据，转换为Shop对象
            Shop shop = JSONUtil.toBean(shopJson, Shop.class);
            return shop;
        }
        //判断命中的是否是空值，因为有空值所以有redis对象，所以不是null
        if(shopJson != null){
            return null;
        }
        Shop shop = null; //定义一个给下面的代码用

        String lockKey = LOCK_SHOP_KEY + id;

        try {
            //4.实现缓存重建
            //4.1 获取互斥锁
            boolean isLock = tryLock(lockKey);
            //4.2 判断是否获取成功
            if(!isLock){
                //4.3 失败，则休眠并重试，重试就是递归
                Thread.sleep(50);
                return queryWithMutex(id);
            }

            //4.4 成功，根据id查询数据库
            shop = getById(id);

            //5.不存在，返回错误
            if (shop == null) {
                //将空值写入redis，防止缓存穿透
                stringRedisTemplate.opsForValue().set(key, "",CACHE_NULL_TTL, TimeUnit.MINUTES);
                return null;
            }

            //6.存在，写入redis缓存
            stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(shop),CACHE_SHOP_TTL, TimeUnit.MINUTES);


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //7.释放互斥锁
            unLock(lockKey);
        }

        //8.返回结果
        return shop;
    }
```



## 11. 逻辑过期解决缓存击穿问题

就算锁获取过期，也照样返回店铺信息，防止出现击穿问题

![image-20251105195525515](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251105195525515.png)

看代码吧：

![image-20251105210249918](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251105210249918.png)

100ms内200次请求的效果：几乎一半是旧数据，一半是新数据

![image-20251107174331450](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251107174331450.png)

## 12.封装工具类解决缓存问题

 ![image-20251106102853334](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251106102853334.png)



# 优惠卷秒杀

## 01.全局ID生成器

![image-20251107204509516](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251107204509516.png)



全局唯一ID生成策略

![image-20251110143644864](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110143644864.png)

## 02.Redis实现全局唯一ID

**Redis ID生成器**

```java
package com.hmdp.utils;

import ...


@Component
public class RedisIdWorker {

  private static final long BEGIN_TIMESTAMP = 1640995200L;

  private  StringRedisTemplate stringRedisTemplate;

   RedisIdWorker(StringRedisTemplate stringRedisTemplate){
    this.stringRedisTemplate = stringRedisTemplate;
   }

    public long nextId(String keyPrefix){
        //1. 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timeStamp = nowSecond - BEGIN_TIMESTAMP;
        
        //2. 生成序列号
        //2.1 获取当前日期，精确到天
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //2.2
        Long count = stringRedisTemplate.opsForValue().increment("icr:"+ keyPrefix + ":" + date );

        //3. 拼接并返回

        return timeStamp << 32 | count;//左移32位，右边32位或运算
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2025, 1, 1, 0, 0, 0);
        long second = localDateTime.toEpochSecond(ZoneOffset.UTC);
        System.out.println(second);

    }
}
```

**单元测试**

```java
@Test
void testRedisIdWorker() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(300); //创建300个任务

    Runnable task = () -> {
        for(int i =0 ;i < 100; i++){
            long id = redisIdWorker.nextId("order");
            System.out.println("id =" + id);
        }
        latch.countDown();
    };
    long begin = System.currentTimeMillis();
    for(int i = 0; i < 300; i++){
        es.submit(task);
    }
    latch.await();
    long end = System.currentTimeMillis();
    System.out.println("耗时：" + (end - begin));
}
```

![image-20251110143015461](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110143015461.png)

![image-20251110143042934](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110143042934.png)



## 3. 优惠卷秒杀：添加优惠卷

![image-20251110191620746](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110191620746.png)

![image-20251110191632126](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110191632126.png)

后台测试：

![image-20251110191452428](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110191452428.png)



## 4. 实现（秒杀）基本下单功能

![image-20251110191717276](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110191717276.png)



![image-20251110201745496](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251110201745496.png)



## 5.库存超卖问题分析

**CAS法**

![image-20251111123002975](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251111123002975.png)



## 6. 乐观锁(改进)解决超卖问题和成功率低的问题

总结：更新优惠卷数据库的那一刻，判断库存是否大于0，就不会出现超卖问题了

看代码就完了：

这里是一人多单

![image-20251111132824188](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251111132824188.png)



## 7. 实现一人一单功能

**重难点**：**悲观锁，spring管理的代理对象与事务**

疑惑点：@Transactional与Spring的事务管理；Spring的代理对象管理，Aop  

看代码：

![image-20251111153633212](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251111153633212.png)



## 8. 集群下的(一人一单)的线程并发安全问题 （分布式锁解决

跨JVM，会出现线程安全问题

![image-20251111174235064](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251111174235064.png)



## 9. 分布式锁-基本原理和不同实现方式对比

![image-20251113142302512](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113142302512.png)



![image-20251113142312636](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113142312636.png)



![image-20251113142519025](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113142519025.png)



## 10.redis分布式锁的实现思路

SET key value NX EX 10

SET lock thread1 NX EX 10

![image-20251113150254453](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113150254453.png)



## 11. 实现Redis分布式锁版本1

锁类代码：

![屏幕截图(1471)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1471).png)

业务层代码：

![image-20251113164101605](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113164101605.png)



## 12. redis分布式锁误删问题

![image-20251113165110884](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113165110884.png)



## 13.解决误删问题（思路看12,）

![image-20251113170827371](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251113170827371.png)

## 14. 判断锁标识和释放锁的命令要具有原子性，中间不能有间隔

判断锁标识和释放锁的命令要具有原子性，中间不能有间隔

![image-20251117160458769](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117160458769.png)



## 15 Lua脚本解决多条命令原子性问题

![image-20251114144028359](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251114144028359.png)

![image-20251114144001880](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251114144001880.png)

## 16.java调用Lua脚本改造分布式锁

![image-20251114150732246](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251114150732246.png)

代码：直接到idea里面看

![image-20251114154351407](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251114154351407.png)

![image-20251114154358697](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251114154358697.png)



# 优惠卷秒杀 （Redisson）

## Redisson配置类

![image-20260326171553114](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20260326171553114.png)

## 17. redisson解释

![image-20251117165123257](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117165123257.png)

![image-20251117161047285](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117161047285.png)



## 18. 快速入门

直接替换自定义锁：

![image-20251117171504986](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117171504986.png)



## 19.  Redisson可重入锁的原理

**两个Lua脚本**：释放锁和获取锁

![image-20251117174732168](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117174732168.png)

![image-20251117174648249](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117174648249.png)



## 20.Redisson的锁重试和WatchDog机制

WatchDog机制：自我更新锁的释放时间

![image-20251117180411116](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117180411116.png)

原理流程图：

![image-20251117182859409](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117182859409.png)



## 21. Redisson的multiLock原理

PS:源码分析过于冗杂，需要时再看

**一致性问题**

![image-20251117200519960](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117200519960.png)



![image-20251117200500371](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117200500371.png)



## 22. 异步秒杀思路

![image-20251117203551504](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117203551504.png)



![image-20251117203702570](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251117203702570.png)



## 23. 基于Redis完成秒杀资格判断 (主线程)



![image-20251118152209382](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251118152209382.png)



![image-20251118152449192](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251118152449192.png)



## 24. 基于阻塞队列实现秒杀异步下单 (子线程)



![image-20251118181722438](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251118181722438.png)

这里的锁因为没必要了

![image-20251118181814476](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251118181814476.png)

创建订单的业务方法，其实只有扣减库存和保存订单信息的mybatis-plus操作就行了，其他判断多余

![image-20251118181906012](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251118181906012.png)



# Redis消息队列

## 25. 认识消息队列

![image-20251119144352117](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119144352117.png)



## 27. PubSub 实现消息队列

![image-20251119150508980](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119150508980.png)

![image-20251119150500109](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119150500109.png)



## 28.Stream的基础用法和单消费模式

发送消息的命令

![image-20251119153637782](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119153637782.png)



读取消息的方式之一：

![image-20251119154503675](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119154503675.png)

实例：

![image-20251119154525071](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119154525071.png)

特点：

![image-20251119154539737](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119154539737.png)



## 29. Stream的消费组模式

![image-20251119160600026](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119160600026.png)

每个消费者都有一个pending-list

![image-20251119155348375](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119155348375.png)



![image-20251119160059544](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119160059544.png)

最后一条看左下角： 0，指定id从pending-list中获取已消费但未确认的消息，从pending-list中的第一个消息开始

![image-20251119160836185](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119160836185.png)

**伪代码**

![image-20251119161404172](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251119161404172.png)



## 30. 基于Stream消息队列实现异步秒杀

这个应该是最终版本

![image-20251120140333636](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251120140333636.png)



seckillVoucher 是主线程，基于redis完成秒杀资格判断的模块代码，子线程是继承了 Runnable()的内部类

![image-20251120141036447](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251120141036447.png)



# 达人探店 

## 1. 发布探店笔记

blogcontroller 保存笔记信息，Uploadcontroller 保存照片到前端服务器中(该项目就是本地),  最后一个是定义常量 ：比如保存图片在本地的地址

![image-20251120201117129](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251120201117129.png)



## 2.  查看探店笔记

第一个函数是查列表，第二个函数是查具体的内容

![image-20251121125645196](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251121125645196.png)



## 3. 点赞功能

实现点赞接口：

![image-20251121150109621](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251121150109621.png)

在具体查询笔记和页查询笔记时，添加 isLike属性标记，是否已经点过赞

![image-20251121150246242](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251121150246242.png)



## 4. 点赞排行榜

将存点赞的set换成能排行的sorted set 

![image-20251125195139092](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251125195139092.png)

业务逻辑：

![image-20251125195053191](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251125195053191.png)

**根据id查询用户时**，mybatis的方法变了 



## 5. 关注和取关

根据接口文档实现两个接口，不涉及redis

![image-20251125210510951](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251125210510951.png)



## 6. 共同关注好友

核心接口：

![image-20251127162530340](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127162530340.png)

关注的接口也要修改：

![image-20251127162607029](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127162607029.png)



## 7. feed流实现方案分析

![image-20251127181455495](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127181455495.png)

![image-20251127181503636](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127181503636.png)

![image-20251127181527145](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127181527145.png)

![image-20251127181539620](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127181539620.png)



## 8.推送到粉丝邮件箱

**不能采用传统的分页模式**

![屏幕截图(1584)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1584).png)

主要内容：**修改新增博客的接口，使其能发送到粉丝的收件箱(redis的)中**

![image-20251127200932850](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127200932850.png)



## 9. 滚动分页查询的思路

ZSET分页查询的命令：

```Redis
根据分数1000到0，降序排      查询结果显示分数    从小于等于1000的第0个查起(偏移量), 查三条
ZREVRANGEBYCORE z1 1000 0 WITHSCORES LIMIT 0 3

```



![image-20251127202457134](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127202457134.png)

参数设置思路：

![image-20251127203455557](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127203455557.png)

![image-20251127203425238](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127203425238.png)

## 10.实现滚动分页查询

主要代码和思路：

![image-20251127213420300](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127213420300.png)

![image-20251127213440625](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251127213440625.png)



## 11. 附近商铺：GEO数据结构的基本用法

![image-20251128121503569](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251128121503569.png)

![image-20251130153057730](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251130153057730.png)

![image-20251130153117673](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251130153117673.png)

![image-20251128121528693](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251128121528693.png)



## 12. 导入店铺数据到GEO

看这个测试代码：

![image-20251130155931418](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251130155931418.png)



## 13.实现附近商户功能

根据商户类型和经纬度坐标x，y 查附近复合要求的所有店铺，获取店铺id集合，再根据from和end分页查询数据库；还有一些异常处理需要注意

![image-20251130175051667](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251130175051667.png)



## 15. BitMap功能演示

![image-20251201165538221](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251201165538221.png)

![image-20251201165513224](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251201165513224.png)



## 16. 实现用户签到功能代码

![image-20251201173032856](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251201173032856.png)



## 17. 统计连续签到

![image-20251201173544036](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251201173544036.png)

![image-20251201184621312](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251201184621312.png)
