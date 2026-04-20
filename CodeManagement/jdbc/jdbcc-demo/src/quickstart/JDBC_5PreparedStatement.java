package quickstart;

import java.sql.*;

public class JDBC_5PreparedStatement {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/mikasa";
        String user = "root";
        String password = "dontshut";
        Connection conn =  DriverManager.getConnection(url,user,password);

        //3.定义sql
        String name = "zhangsan";
        String pwd = "123456";

        //定义sql
        String sql = "select * from t_table where username = ? and password = ?";


        //4.获取presta对象
        PreparedStatement presta = conn.prepareStatement(sql);

        //设置？的值
        presta.setString(1, name);
        presta.setString(2, pwd);

        //执行sql
        ResultSet rs = presta.executeQuery();

        //判断登陆是否成功
        if(rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }


        //7. 释放资源
        rs.close();
        presta.close();
        conn.close();
    }
}
