package com.projectprac.tlias_prac.service.impl;

import com.projectprac.tlias_prac.mapper.DeptMapper;
import com.projectprac.tlias_prac.mapper.EmpMapper;
import com.projectprac.tlias_prac.pojo.Dept;
import com.projectprac.tlias_prac.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service //控制转换
public class DeptServiceimpl implements DeptService {

    @Autowired//依赖注入接口Mapper的实现类对象,所以能调用接口内的方法
    private DeptMapper deptMapper;//service层不能直接访问数据,所以要调用Mapper接口
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
     return deptMapper.list();
    }

    @Transactional(rollbackFor = Exception.class) //开启spring事务管理，若遇到异常则进行事务回滚
    @Override
    public boolean delete(Integer id) {

        deptMapper.deleteById(id);


        empMapper.deleteByDeptID(id);
        return true;
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
