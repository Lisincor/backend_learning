package mapper;

import org.apache.ibatis.annotations.*;
import pojo.Emp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface EmpMapper {
    //根据ID删除数据
    @Delete("DELETE from emp where id = #{id}")
    public void delete(Integer id);

    //新增数据
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time)" +
            "values (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime});")
    public void insert(Emp emp);

    //更新数据
    @Update("update emp set username = #{username}, name = #{name}, gender = #{gender}, image = #{image}, job = #{job},entrydate = #{entrydate},dept_id = #{deptId},update_time=#{updateTime} where id = #{id};")
    public void update(Emp emp);
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

    //条件查询
    //                                          此处下面的不能用#{},因为''里面不能有映射的问号,只能用${}
//    @Select("select * from emp where name like '%${name}%' and gender = #{gender} and " +
//            "entrydate between #{begin} and #{end} order by update_time desc;")
//    public List<Emp> select2(String name, Short gender, LocalDate begin, LocalDate end);

    //条件查询
    //运用字符串拼接函数解决这个问题
//    @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} and " +
//            "entrydate between #{begin} and #{end} order by update_time desc;")
//    public List<Emp> select3(String name, Short gender, LocalDate begin, LocalDate end);

    public List<Emp> select3(String name, Short gender, LocalDate begin, LocalDate end);


    //动态更新员工
    public void update2(Emp emp);

    //批量删除员工
    public void deleteByID(List<Integer> ids);



}
