package com.projectprac.tlias_prac.service;

import com.projectprac.tlias_prac.pojo.Emp;
import com.projectprac.tlias_prac.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface EmpService {
    /**
     * 实现分页查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize,String name, Short gender,
                  LocalDate begin, LocalDate end);

    void delete(List<Integer> ids);

    void save(Emp emp);

    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
