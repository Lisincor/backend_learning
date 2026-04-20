package com.itheima.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.itheima.controller") //扫描包，在web容器中加载对应的bean
public class SpringMvcConfig {
}
