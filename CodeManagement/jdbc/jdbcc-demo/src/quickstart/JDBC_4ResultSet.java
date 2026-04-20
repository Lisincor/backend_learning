package quickstart;

import java.sql.*;

public class JDBC_4ResultSet {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.注册驱动:在jdbc5版本后可以不写注册驱动这一行
        //Class.forName("com.mysql.jdbc.Driver");

        //2.获取连接
        String url = "jdbc:mysql://localhost:3306/mikasa";
        String user = "root";
        String password = "dontshut";
        Connection conn =  DriverManager.getConnection(url,user,password);

        //3.定义sql
        String sql = "select * from t_book";

        //4.获取sql的执行对象 Statement
        Statement sta = conn.createStatement();

        //5.执行sql
        ResultSet count = sta.executeQuery(sql); //该方法返回受影响的行数

        //6.循环查询数据
        while(count.next()){
            int id = count.getInt(1);
            String name = count.getString("name");
            String author = count.getString("author");
            double price = count.getDouble(4);

            System.out.println(id);
            System.out.println(name);
            System.out.println(author);
            System.out.println(price);

            System.out.println("-----------");
        }


        //7. 释放资源
        count.close();
        sta.close();
        conn.close();
    }
}
