package com.projectprac.tlias_prac.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.projectprac.tlias_prac.mapper.EmpMapper;
import com.projectprac.tlias_prac.pojo.Emp;
import com.projectprac.tlias_prac.pojo.PageBean;
import com.projectprac.tlias_prac.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceimpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender,
                         LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);
        //2.执行查询
        List<Emp> emplist = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) emplist;

        //3.封装PageBean对象
        PageBean pageBean = new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    //插入员工信息时，补充信息
    @Override
    public void save(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        emp.setCreateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.GetByUsernameandPassword(emp);
    }
}
