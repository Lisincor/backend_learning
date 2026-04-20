package com.itheima;

import com.itheima.dao.BookDao;
import com.itheima.service.BookService;
import com.itheima.service.impl.BookServiceimpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class APP {
    public static void main(String[] args) {
        //获取IOc容器
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        //获取bean
        BookService bs = (BookService) app.getBean("bookService");


        bs.save();

     }
}
