package com.itheima.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

//4.定义一个容器启动的配置类，加载spring的配置
public class ServletConfig extends AbstractDispatcherServletInitializer {
    //加载SpringMvc容器配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(SpringMvcConfig.class); //加载具体的配置
        return ctx;
    }

    //哪些请求归SpringMVc管理
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    //加载Spring容器配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
