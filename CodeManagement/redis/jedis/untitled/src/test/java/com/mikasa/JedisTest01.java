package com.mikasa;

import com.mikasa.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest01 {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        jedis = JedisConnectionFactory.getJedis();
        jedis.auth("123456");
        jedis.select(0);
    }


    @Test
     void testString(){
        String result = jedis.set("name2","huge2");
        System.out.println(result);

        String name = jedis.get("name2");
        System.out.println(name);
    }

    @AfterEach
    void tearDown() {
        if(jedis != null){
            jedis.close();
        }
    }
}
