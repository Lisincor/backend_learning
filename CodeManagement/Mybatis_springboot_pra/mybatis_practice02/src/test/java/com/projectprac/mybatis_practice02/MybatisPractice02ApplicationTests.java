package com.projectprac.mybatis_practice02;

import mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MapperScan("mapper")
@SpringBootTest
class MybatisPractice02ApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void deletetest(){
        empMapper.delete(16);
    }

    @Test
    public void inserttest(){
        Emp emp = new Emp();

        emp.setUsername("Tom2");
        emp.setName("汤姆2");
        emp.setImage("12.img");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        empMapper.insert(emp);
        System.out.println(emp.getId());
}

    //更新员工信息
   @Test
    public void updatetest(){
        Emp emp = new Emp();
       emp.setId(18);
       emp.setUsername("Tom3");
       emp.setName("汤姆1");
       emp.setImage("114.img");
       emp.setGender((short)1);
       emp.setJob((short)1);
       emp.setEntrydate(LocalDate.of(2000,1,1));
       emp.setUpdateTime(LocalDateTime.now());
       emp.setDeptId(1);

       empMapper.update(emp);
    }

    //根据id查询员工信息
    @Test
    public void selecttest(){
        Emp emp = empMapper.select(18);
        System.out.println(emp);
    }

    //根据条件查询员工信息
    @Test
    public void selecttest1(){
        List<Emp> empList= empMapper.select3(null,null,null,null);
        empList.forEach(System.out::println);
    }


    //动态更新员工信息
    @Test
    public void updatetest2(){
        Emp emp = new Emp();
        emp.setId(18);
        emp.setUsername("Tom333");
        emp.setName("汤姆1");
        emp.setGender((short)2);
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);
    }

    //批量删除员工
    @Test
    public void deletetest1(){
        List<Integer> ids = Arrays.asList(16,18,19);
        empMapper.deleteByID(ids);
    }


}
