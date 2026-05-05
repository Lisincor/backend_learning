# Mybatis

## 1.mybatis入门及核心

### 核心:

mapper:在运行时,会自动生成该接口的实现类对象(代理对象),并将该对象交给IOC容器管理

```java
package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import pojo.Emp;

@Mapper
public interface EmpMapper {
    //根据ID删除数据
    @Delete("DELETE from emp where id = #{id}")
    public void delete(Integer id);

    //新增数据
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values ('Tom','汤姆',1,'1.img',1,'2005-01-01',1,now(),now());")
    public void insert(Emp emp);

}


```

依赖注入该接口的实现类

```java
package com.projectprac.mybatis_practice02;

import mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("mapper")
@SpringBootTest
class MybatisPractice02ApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void deletetest(){
        empMapper.delete(16);
    }
}
```



![image-20251001201912579](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251001201912579.png)



引入mybatis相关依赖和配置mybatis数据库连接信息

![屏幕截图(1091)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1091).png)



## 2.mybatis与jdbc比较

![屏幕截图(1096)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1096).png)



## 3.数据库连接池

介绍：

![image-20251002100444498](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251002100444498.png)

所有的数据库连接池都要实现dataSource接口

![image-20251002100305469](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251002100305469.png)

引入druid连接池

![image-20251002100106987](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251002100106987.png)



## 4.lombok

![image-20251002103959814](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251002103959814.png)

```java
package pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //全参构造
@NoArgsConstructor  //无参构造
public class Book {


    private Integer id     ;
    private String name   ;
    private String author ;
    private Double price  ;

//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", author='" + author + '\'' +
//                ", price=" + price +
//                '}';
//    }

//    public Book() {
//    }
//
//    public Book(Integer id, String name, String author, Double price) {
//        this.id = id;
//        this.name = name;
//        this.author = author;
//        this.price = price;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}

```



## 5. 项目创建流程

![image-20251002112720790](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251002112720790.png)



## 6.Mybatis进行数据库操作

### 1. 删除

```java
package com.projectprac.mybatis_practice02;

import mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@MapperScan("mapper")
@SpringBootTest
class MybatisPractice02ApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void deletetest(){
        empMapper.delete(16);
    }
}
```

### 2.预编译

![屏幕截图(1108)](https://raw.githubusercontent.com/Lisincor/image-hosting/main/屏幕截图(1108).png)

```java
@Mapper
public interface EmpMapper {
    //根据ID删除数据
    @Delete("DELETE from emp where id = #{id}") //使用#{}时，mybatis就是自动调用预编译功能
    public void delete(Integer id);
}
```



### 3. 添加数据以及获取返回主键

mapper

```java
package mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import pojo.Emp;

@Mapper
public interface EmpMapper {
    
    //获取返回主键
    //第一个参数的意思:生成主键为true,第二个参数的意思是:返回的主键返回到emp对象的id属性中
    @Options(useGeneratedKeys = true,keyProperty = "id")
    
    //新增数据
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    public void insert(Emp emp);

}
```

测试类

```java
package com.projectprac.mybatis_practice02;

import mapper.EmpMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MapperScan("mapper")
@SpringBootTest
class MybatisPractice02ApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void selecttest(){
        Emp emp = new Emp();

        emp.setUsername("Tom");
        emp.setName("汤姆");
        emp.setImage("1.img");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        //调用接口方法
        empMapper.insert(emp);
        //打印返回的主键
        System.out.println(emp.getId());
}
}

```

### 4.更新

mapper(接口方法)

```java
package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Emp;

@Mapper
public interface EmpMapper {

    //接口方法
    //更新数据
    @Update("update emp set username = #{username}, name = #{name}, gender = #{gender}, image = #{image}, job = #{job},entrydate = #{entrydate},dept_id = #{deptId},update_time=#{updateTime} where id = #{id};")
    public void update(Emp emp);

}

```



```java
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


```



### 5.查询(根据id查询)

查询时的数据封装

![image-20251003140402320](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251003140402320.png)

mapper

```java
package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Emp;

@Mapper
public interface EmpMapper {
    //方案三:打开mybatis的驼峰命名的自动开关
    //查询数据
    @Select("select * from emp where id = #{id};")
    public Emp select(Integer id); //根据id查询,返回Emp对象

    //方案一:给字段起别名,与实体类中的属性名一致
//    @Select("select id, username, password, name, gender, image, job, entrydate, " +
//            "dept_id deptId, create_time createTime, update_time updateTime from emp where id = #{id};")
//    public Emp select(Integer id); //根据id查询,返回Emp对象

    //方案二:通过@Results,@Result手动映射封装
//    @Results({
//            @Result(column ="dept_id" ,property ="deptId" ),
//            @Result(column ="create_time" ,property ="createTime" ),
//            @Result(column ="update_time" ,property ="updateTime" )
//    })
//    @Select("select * from emp where id = #{id};")
//    public Emp select(Integer id); //根据id查询,返回Emp对象

    //方案三:打开mybatis的驼峰命名的自动开关
}

```



### 6.XML映射文件 与 动态sql一起

**三条规范,很重要**

![image-20251003154642377](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251003154642377.png)

mapper

```java
package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {
    public List<Emp> select3(String name, Short gender, LocalDate begin, LocalDate end);
}
```

xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="mapper.EmpMapper">

<!--  resultType == 单条记录所封装的类型-->
    <select id="select3" resultType="pojo.Emp" >
        select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and entrydate between #{begin} and #{end} order by update_time desc;
    </select>

</mapper>
```

ps:如果使用一些简单的增删改查,可以用注解;如果要用复杂的sql语句,用xml映射



## 7.动态sql 与 XML映射文件一起

### 1.if,where,set

先看总结

![image-20251003165825558](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251003165825558.png)

约束在mybatis中文网上找，约束：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

if,where,set

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.EmpMapper">
    <update id="update2">
        update emp
            <set>
             <if test="username != null">username = #{username},</if>
            name       = #{name},
            gender     = #{gender},
            image      = #{image},
            job        = #{job},
            entrydate  = #{entrydate},
            dept_id    = #{deptId},
            update_time=#{updateTime}
            </set>
        where id = #{id};
    </update>

    <!--    单条记录所封装的类型-->
    <select id="select3" resultType="pojo.Emp" >
        select *
        from emp
        <where>
        <if test="name != null">
            name like concat('%', #{name}, '%')
        </if>
        <if test="gender != null">
            and gender = #{gender}
        </if>
        <if test="begin != null and end != null">
            and entrydate between #{begin} and #{end}
        </if>
        </where>
    order by update_time desc;
    </select>

</mapper>
```



### 2.foreach

测试

```java
//批量删除员工
    @Test
    public void deletetest1(){
        List<Integer> ids = Arrays.asList(16,18,19);
        empMapper.deleteByID(ids);
    }
```



mapper

```java
package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {
    //批量删除员工
    public void deleteByID(List<Integer> ids);

}
```



xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.EmpMapper">

    <delete id="deleteByID">
        delete from emp where id in //ctrl + alt + l格式化
        <foreach collection="ids" item="id" separator="," open="("  close=")" >
            #{id}
        </foreach>

    </delete>

</mapper>
```

### 3.sql与include(封装sql语句，提高复用性)

注意看<sql>和<include>标签

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="mapper.EmpMapper">
    <sql id = "CommomSelect"> //标明sql标签的id
        select id,username,id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time
        from emp
    </sql>


    <update id="update2">
        update emp
            <set>
             <if test="username != null">username = #{username},</if> <!--下面的同样用if标签-->
            name       = #{name},
            gender     = #{gender},
            image      = #{image},
            job        = #{job},
            entrydate  = #{entrydate},
            dept_id    = #{deptId},
            update_time=#{updateTime}
            </set>
        where id = #{id};
    </update>


    <!--    单条记录所封装的类型-->
    <select id="select3" resultType="pojo.Emp" >
        <include refid="CommomSelect"/>

        <where>
        <if test="name != null">
            name like concat('%', #{name}, '%')
        </if>
        <if test="gender != null">
            and gender = #{gender}
        </if>
        <if test="begin != null and end != null">
            and entrydate between #{begin} and #{end}
        </if>
        </where>
    order by update_time desc;
    </select>

    <delete id="deleteByID">
        delete from emp where id in
        <foreach collection="ids" item="id" separator="," open="("  close=")" >
            #{id}
        </foreach>
    </delete>



</mapper>
```









# MybatisPlus

![image-20251106150804418](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106150804418.png)

## 2. 标准CRUD

![image-20251106192724085](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106192724085.png)

## 4.分页查询

![image-20251106152706824](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106152706824.png)

![image-20251106152724054](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106152724054.png)

### 4.2**mybatis日志**

![image-20251106152823034](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106152823034.png)

![image-20251106173039443](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106173039443.png)

## 5. 条件查询的三种方式

![image-20251106165603329](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106165603329.png)

**lambda**

![image-20251106170533788](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106170533788.png)

![image-20251106170453059](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106170453059.png)



## 6. null 值处理

![image-20251106171806631](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106171806631.png)



## 7.查询投影

![image-20251106173447895](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106173447895.png)



## 9. 映射匹配兼容性

![image-20251106180813222](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106180813222.png)



## 10. id生成策略

![image-20251106192422331](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106192422331.png)

![image-20251106192433080](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106192433080.png)



## 11.逻辑删除

![image-20251106200615552](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106200615552.png)



## 12. Service接口



![image-20251106203035258](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251106203035258.png)

![image-20251107140055416](https://raw.githubusercontent.com/Lisincor/image-hosting/main/image-20251107140055416.png)
