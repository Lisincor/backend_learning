package com.projectprac.tlias_prac.mapper;

import com.projectprac.tlias_prac.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     * @return
     */
//     @Select("Select count(*) from emp")
//    public Long count();

    /**
     * 分页查询,获取列表数据
     * @return
     */
//    @Select("Select * from emp limit #{start},#{pageSize}")
//    public List<Emp> page(Integer start,Integer pageSize);

    /**
     *
     * @return
     */

    public List<Emp> list(String name, Short gender,
                          LocalDate begin, LocalDate end);

    public void delete(List<Integer> ids);


    /**
     * 新增员工
     * @param emp
     */
    //sql比较简单，用注释写
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
    "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate},#{deptId},#{createTime},#{updateTime})")
    public void insert(Emp emp);

    /**
     * 根据Id查询员工
     * @param id
     * @return
     */
    @Select("select * from emp where id = #{id}")
    Emp getById(Integer id);

    /**
     * 修改员工
     * @param emp
     */
    void update(Emp emp);

    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp GetByUsernameandPassword(Emp emp);

    /**
     *  根据部门ID删除员工
     * @param id
     */
    @Delete("delete from emp where dept_id = #{id}")
    void deleteByDeptID(Integer id);
}
