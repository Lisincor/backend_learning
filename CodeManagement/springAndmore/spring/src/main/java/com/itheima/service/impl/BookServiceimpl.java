package com.itheima.service.impl;

import com.itheima.dao.BookDao;
import com.itheima.dao.impl.BookDaoimpl;
import com.itheima.service.BookService;

public class BookServiceimpl implements BookService {
    private BookDao bookDao ;
    @Override
    public void save() {
        System.out.println("peint bookservice");
        bookDao.save();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}
