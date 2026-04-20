package com.projectprac.tlias_prac.controller;

import com.projectprac.tlias_prac.anno.Log;
import com.projectprac.tlias_prac.pojo.Dept;
import com.projectprac.tlias_prac.pojo.Result;
import com.projectprac.tlias_prac.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j //log输出日志,就是下面的log.info("xxx")
@RequestMapping("/depts")
@RestController //该注解包含了ResponseBody注解,会将相应返回的Result对象转为json格式返回
                   //整合了控制转换@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    //有了@Slf4j注解,就不用自己写日志logger对象了
//    private static Logger logger = LoggerFactory.getLogger(DeptController.class);

    //@RequestMapping("/depts") //
    @GetMapping //限定了请求方式GET
    public Result list(){

        //调用service查询部门数据
        List<Dept> deptList = deptService.list();

        log.info("全部部门信息");
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除部门:{}",id);//{}是占位符,id占位{}

        boolean l = deptService.delete(id);
        return l ? Result.success() : Result.error("查询失败");
    }

    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
      log.info("新增部门 {}",dept);
      deptService.add(dept);
      return Result.success();
    }


}
