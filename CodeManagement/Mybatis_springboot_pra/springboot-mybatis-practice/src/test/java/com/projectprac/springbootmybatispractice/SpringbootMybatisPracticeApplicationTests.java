package com.projectprac.springbootmybatispractice;

import jakarta.annotation.Resource;
import mapper.BookMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pojo.Book;

import java.util.List;

@MapperScan("mapper") // 修改为你的实际包名
@SpringBootTest
class SpringbootMybatisPracticeApplicationTests {

    @Autowired(required = true)
    private BookMapper bookMapper;
    @Test
    public void TestAll() {
        List<Book> books = bookMapper.findAll();
       for(Book book:books){

           System.out.println(book);
       }
    }

}
