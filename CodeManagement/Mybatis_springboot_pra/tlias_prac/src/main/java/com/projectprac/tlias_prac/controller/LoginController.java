package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.pojo.Emp;
import com.projectprac.tlias_prac.pojo.Result;
import com.projectprac.tlias_prac.service.EmpService;
import com.projectprac.tlias_prac.utils.JwtUtils;
import io.jsonwebtoken.Jwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
      log.info("员工从参数：{}",emp);
      Emp e =  empService.login(emp);

      //如果用户名密码成立，生成JWT令牌
      if(e != null){
          Map<String, Object> claims = new HashMap<>();
          claims.put("id",e.getId());
          claims.put("name",e.getName());
          claims.put("username",e.getUsername());

          String jwt = JwtUtils.generateJwt(claims); //jwt包含了当前登录员工的信息
          return Result.success(jwt);
      }

      return  Result.error("用户名或密码错误");
    }
}
