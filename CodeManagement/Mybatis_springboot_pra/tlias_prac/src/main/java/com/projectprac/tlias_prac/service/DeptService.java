package com.projectprac.tlias_prac.service;

import com.projectprac.tlias_prac.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    /**
     * 查询全部部门数据
     *
     */
    public List<Dept> list(); //接下来到接口实现类DeptServiceimpl重写方法

    /**
     * 删除部门
     */
    boolean delete(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);
}
