# Redis的连接

在配置文件配置Redis服务端接口

![image-20260326162640143](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20260326162640143.png)

# Redis命令

## 1. 数据结构

![image-20251024152837109](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251024152837109.png)



## 2.通用命令

![image-20251024154312990](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251024154312990.png)

keys *  (性能太低不建议使用)

del key_name

exists ： 判断key是否存在

expire key_name

ttl key_name



## 3. String类型和基本命令

![image-20251027103503243](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027103503243.png)



常见命令：

![image-20251027103440225](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027103440225.png)

set age 18 

get age

mset age1 19 age2 29  age3 10.5

mget age1 age2

incr age  

incrby age 2 

incrbyfloat age3 0.5

setnx 

setex (与expire的结合) ： setex age 100 19



## 4. key的层级存储

![image-20251027104459373](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027104459373.png)



## 5. hash常见命令



![image-20251027105111530](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027105111530.png)



## 6. List常见命令

![image-20251027111057170](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027111057170.png)



```
stringRedisTemplate.opsForList().range(CACHE_SHOP_TYPE_LIST_KEY, 0, -1);
```



这是一个典型的 Spring Data Redis 中通过 `StringRedisTemplate` 操作 List 类型数据的代码。

### 1. 方法链分解

首先，我们分解一下整个方法调用：

- **`stringRedisTemplate`**: Spring 提供的用于操作 Redis 的模板类，特别适用于 String 序列化器。
- **`.opsForList()`**: 这个方法返回一个 `ListOperations` 对象，该对象专门封装了所有用于操作 Redis 中 List 数据结构的命令。
- **`.range(CACHE_SHOP_TYPE_LIST_KEY, 0, -1)`**: 这是核心方法，它执行 Redis 的 `LRANGE` 命令。

------

### 2. `range` 方法语法分析

`range` 方法的完整签名通常如下：

java

```
List<V> range(K key, long start, long end)
```



让我们逐一分析它的三个参数：

| 参数      | 位置 | 类型   | 描述                     | 示例中的值                 | 说明                                                        |
| :-------- | :--- | :----- | :----------------------- | :------------------------- | :---------------------------------------------------------- |
| **key**   | 1    | `K`    | Redis 中 List 数据的键名 | `CACHE_SHOP_TYPE_LIST_KEY` | 一个常量，代表存储店铺类型列表的键，例如 `"shop:type:list"` |
| **start** | 2    | `long` | 范围的起始索引           | `0`                        | **索引从 0 开始**。`0` 代表列表的第一个元素。               |
| **end**   | 3    | `long` | 范围的结束索引           | `-1`                       | **`-1` 代表列表的最后一个元素**。这是一个非常常用的约定。   |

------

### 3. 参数详解与示例

#### 起始索引 (`start`)

- Redis List 的索引是 **从 0 开始** 的。
- `0` 表示第一个元素。
- 如果使用负数，表示从列表末尾开始计数。例如，`-1` 表示最后一个元素，`-2` 表示倒数第二个，依此类推。

#### 结束索引 (`end`)

- `-1` 是一个特殊值，它代表列表的最后一个元素。
- 所以，组合 `(0, -1)` 的意思就是 **“从第一个元素到最后一个元素”**，即**获取整个列表**

![image-20251027111129863](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027111129863.png)



## 7. set类型和常用命令

![image-20251027113535609](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027113535609.png)

常用命令

![image-20251027113341573](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027113341573.png)



## 8. sortset类型和常用命令

![image-20251027164417340](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027164417340.png)

![image-20251027164258023](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027164258023.png)

在命令z字母后加rev，数据按降序排序

![image-20251027164331796](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027164331796.png)



# Redis的java客户端

## 1.  jedis基本操作



![image-20251027183134442](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251027183134442.png)



## 2. jedis的连接池

连接池工厂类

```java
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
```



```java
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
}
```



# SpringData Redis

## 1. 介绍

![屏幕截图(1363)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1363).png)

![屏幕截图(1364)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1364).png)



## 2. Redis的序列化工具RedisSerializer

**默认采用jdk序列化**

![image-20251028171959143](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251028171959143.png)

![image-20251028171322676](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251028171322676.png)

自定义RedisTemplate序列化方式

代码：

```java
package com.projectprac.springredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean               //通过泛型来指定
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory connectionFactory){
        //创建RedisTemplate对象
        RedisTemplate<String,Object> template = new RedisTemplate<>();
        //设置连接工厂
        template.setConnectionFactory(connectionFactory);
        //创建JSON序列化工具
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        //设置key的序列化
        template.setKeySerializer(RedisSerializer.string());
        template.setHashKeySerializer(RedisSerializer.string());
        //设置value的序列化
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        //返回
        return template;
    }
}
```

测试类

```java
package com.projectprac.springredis;

import com.projectprac.springredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class SpringredisApplicationTests {

    @Autowired           //依赖注入时也通过泛型指定
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
```



## 3. StringRedisTemplate序列化

![image-20251031205242941](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251031205242941.png)

![image-20251028185600659](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251028185600659.png)

```java
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

    //ObjectMapper是springMvc默认的Jsons序列化处理工具
    private static final ObjectMapper mapper = new ObjectMapper(); 

    @Test
    void test02() throws JsonProcessingException {
        User user = new User("三笠",18);
        //手动序列化 ：将user对象的属性转成json
        String json = mapper.writeValueAsString(user);
        //写入数据
        redisTemplate.opsForValue().set("user:100",json);


        //读取数据
        String json2 = redisTemplate.opsForValue().get("user:100");
        //手动反序列化：将json转换成对象的属性
        User user1 = mapper.readValue(json2,User.class);
        System.out.println(user1);
    }

}
```

