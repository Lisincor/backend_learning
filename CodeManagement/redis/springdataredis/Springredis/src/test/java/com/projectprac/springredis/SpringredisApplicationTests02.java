package com.projectprac.springredis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectprac.springredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class SpringredisApplicationTests02 {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name","huge2222");

        Object name = redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void test02() throws JsonProcessingException {
        User user = new User("三笠",18);
        //手动序列化
        String json = mapper.writeValueAsString(user);
        //写入数据
        redisTemplate.opsForValue().set("user:100",json);


        //读取数据
        String json2 = redisTemplate.opsForValue().get("user:100");
        //手动反序列化
        User user1 = mapper.readValue(json2,User.class);
        System.out.println(user1);
    }

    @Test
    void test03(){
        redisTemplate.opsForHash().put("user:101","name","艾伦");
        redisTemplate.opsForHash().put("user:101","age","18");
    }

}
