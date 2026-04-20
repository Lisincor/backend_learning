package com.hmdp.utils;

import cn.hutool.core.lang.UUID;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class SimpleRedisLock implements ILcok{

    private String name;
    private StringRedisTemplate stringRedisTemplate;

    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID().toString();
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT; //创建脚本对象
    //静态代码块配置脚本对象
    static {
    UNLOCK_SCRIPT = new DefaultRedisScript<>();
    UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua")); //直接在类当前目录下面找
    UNLOCK_SCRIPT.setResultType(Long.class);
    }




    @Override
    public boolean tryLock(long timeoutSec) {
        // 获取线程ID
        String threadID = ID_PREFIX + Thread.currentThread().getId();
        //获取锁 setIfAbsent 具有互斥效果
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(KEY_PREFIX + name, threadID, timeoutSec, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(success); //如果success是null，那么自动拆箱后会出错，调用api后如果success是null，也会返回fasle
    }

//    @Override
//    public void unlock() {
//        //获取线程标示
//        String threadID = ID_PREFIX + Thread.currentThread().getId();
//        //获取锁中的标示
//        String id = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
//        //判断标示是否一致
//        if(threadID.equals(id)){
//            //释放锁
//            stringRedisTemplate.delete(KEY_PREFIX + name);
//        }
//    }

    @Override
    public void unlock() {
        //调用lua脚本
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX + Thread.currentThread().getId()
        );
    }
}
