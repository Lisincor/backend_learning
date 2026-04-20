package quickstart;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class JDBC_2 {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动:在jdbc5版本后可以不写注册驱动这一行
        Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/mikasa";
        String user = "root";
        String password = "dontshut";
        Connection conn =  DriverManager.getConnection(url,user,password);

        //3.定义sql
        String sql = "update t_book set price = 919.3 where id = 2";

        //4.获取sql的执行对象 Statement
        Statement sta = conn.createStatement();

        //开启事务
        conn.setAutoCommit(false);
        try {
            //5.执行sql
            int count = sta.executeUpdate(sql); //该方法返回受影响的行数

            //6. 处理结果
            System.out.println(count);

            //提交事务
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }


        //提交事务


        //7. 释放资源
        sta.close();
        conn.close();
    }
}
