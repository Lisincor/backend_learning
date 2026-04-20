package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.anno.Log;
import com.projectprac.tlias_prac.pojo.Emp;
import com.projectprac.tlias_prac.pojo.PageBean;
import com.projectprac.tlias_prac.pojo.Result;
import com.projectprac.tlias_prac.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emps") //@RequestParam 设置参数默认值
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询,参数{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);

        PageBean pageBean = empService.page(page,pageSize,name,gender,begin,end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/emps/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作：{}",ids);
        empService.delete(ids);
        return Result.success();
    }

    @Log
    @PostMapping("/emps")
    public Result add(@RequestBody Emp emp) {
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工的ID:{}",id);
        Emp emp = empService.getById(id);

        return Result.success(emp);
    }

    @Log
    @PutMapping("/emps")
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息：{}",emp);
        empService.update(emp);
        return Result.success();
    }
}
