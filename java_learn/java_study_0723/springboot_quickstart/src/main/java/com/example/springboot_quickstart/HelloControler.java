package com.example.springboot_quickstart;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println();
        return "koni Spring Boot !";
    }

    @RequestMapping("/para")
    public String para(String name,int age){
        System.out.println(name + " " + age);
        return  name+" para " + age;
    }
}
