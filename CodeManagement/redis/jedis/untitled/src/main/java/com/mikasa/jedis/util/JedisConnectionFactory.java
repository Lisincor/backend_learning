package com.mikasa.jedis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisConnectionFactory {
    private static final JedisPool jedispool;

    static {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(8);//最大连接数
        poolConfig.setMaxIdle(8);
        poolConfig.setMinIdle(0);
        poolConfig.setMaxWaitMillis(1000);
        //创建连接池对象
        jedispool = new JedisPool(poolConfig,"192.168.88.128",6379,1000,"123456");

    }

    public static Jedis getJedis(){
        return jedispool.getResource();
    }

}
