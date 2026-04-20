# 事务管理



## 1.Transactional 

@Transactional注解：通常是在业务层操作，因为业务层通常包含多个业务逻辑的操作；而且一般是加在业务层的增删改的包含多次操作数据库的方法中；也可以加在接口和类中，接口的实现类继承，类中的方法也能进行事务管理

![image-20251012115758019](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012115758019.png)



实例代码：

 ```java
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
 
     @Transactional //开启spring事务管理，若遇到异常则进行事务回滚
     @Override
     public void delete(Integer id) {
 
         deptMapper.deleteById(id);
         int i = 1 / 0; //模拟抛出异常
 
         empMapper.deleteByDeptID(id);
     }
 }
 ```

### 事务的日志配置

![image-20251018130621984](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251018130621984.png)

## 2.rollbackFor属性

![image-20251012133854888](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012133854888.png)

## 3.事务属性的传播行为

![屏幕截图(1185)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1185).png)

**例子：**

将记录日志的方法设置为创建新的事务

![屏幕截图(1188)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1188).png)



# AOP

## 1.AOP快速入门：

1.引入依赖

2.编写AOP程序：针对特定方法根据业务需要进行编程

```java
package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect //在这个注释下@Aspect的切面类 
public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))") //切入点表达式
    public void pt(){}

    @Before("pt()")
    public void before(){
        log.info("before ...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before ...");

        //调用目标对象的原始方法执行
        Object result = proceedingJoinPoint.proceed();

        log.info("around after ...");
        return result;
    }

    @After("pt()")
    public void after(){
        log.info("after ...");
    }

    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning ...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("afterThrowing ...");
    }
}
```



![image-20251012144731815](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012144731815.png)



## 2. AOP核心概念和执行流程

核心概念：

注意区分连接点和切入点

![image-20251012150025969](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012150025969.png)

执行流程： 目标对象会先生成代理对象，代理对象交给IOC容器管理

![屏幕截图(1192)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1192).png)



## 3.通知类型和@Pointcut注解

通知类型：看代码

```java
package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect
public class MyAspect1 {

    @Pointcut("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))") //切入点表达式
    public void pt(){}

    @Before("pt()")
    public void before(){
        log.info("before ...");
    }

    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before ...");

        //调用目标对象的原始方法执行
        Object result = proceedingJoinPoint.proceed();

        log.info("around after ...");
        return result;
    }

    @After("pt()")
    public void after(){
        log.info("after ...");
    }

    @AfterReturning("pt()")
    public void afterReturning(){
        log.info("afterReturning ...");
    }

    @AfterThrowing("pt()")
    public void afterThrowing(){
        log.info("afterThrowing ...");
    }
}
```



![image-20251012153914042](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012153914042.png)

![image-20251018121818828](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251018121818828.png)

@PointCut注解

![image-20251012153816579](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012153816579.png)



## 4.通知顺序

![image-20251012170842941](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012170842941.png)



## 5.切入点表达式和书写规范（过于繁杂，具体看业务开发）

![屏幕截图(1201)](C:\Users\19776\Pictures\Screenshots\屏幕截图(1201).png)

书写规范：

![image-20251012173017976](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012173017976.png)



## 6.根据指定的注解写切入点表达式（最为常用）

（图中的是自定义注解）

![image-20251012173517756](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012173517756.png)



## 7.连接点和获取目标方法参数

![image-20251012174609220](C:\Users\19776\AppData\Roaming\Typora\typora-user-images\image-20251012174609220.png)



# AOP实例

具体看tilas_prac项目