package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

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
        //2.2 (难点) 当天的自增长
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
