package com.projectprac.springredis;

import com.projectprac.springredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringredisApplicationTests {

    @Autowired
    private RedisTemplate<String,Object>  redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name","艾伦");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    @Test
    void test2(){
        redisTemplate.opsForValue().set("User",new User("艾伦",18));

        Object o = redisTemplate.opsForValue().get("User");
        System.out.println(o);
    }

}
