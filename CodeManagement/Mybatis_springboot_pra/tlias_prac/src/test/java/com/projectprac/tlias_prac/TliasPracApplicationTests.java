package com.projectprac.tlias_prac;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasPracApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成jwt
     */
    @Test
    public void JWTtest(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("username","tom");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"mikasa")//这里的字符串是密钥secret
                .setClaims(map)//自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))//设置有效期
                .compact();//jwt令牌转换为字符串
        System.out.println(jwt);
    }

    /**
     * 解析JWT
     */
    @Test
    public void JWTtest1(){
      Claims claims = Jwts.parser()
               .setSigningKey("mikasa")
               .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzYwMDgzMzcwLCJ1c2VybmFtZSI6InRvbSJ9.s3qge4YEMjHn7u7O95UJtV4-uGnG5Pi0LWgYt_ugUAY")
               .getBody();
        System.out.println(claims);
    }

}
