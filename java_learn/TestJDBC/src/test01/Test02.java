package test01;

import java.sql.*;

public class Test02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //获取连接
        String url ="jdbc:mysql://127.0.0.1:3306/mikasa?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username="root";
        String password = "dontshut";
        Connection conn = DriverManager.getConnection(url, username, password);
        //创建会话
        Statement sta = conn.createStatement();
        //发送sql: ResultSet结果集合 - 结果集
        ResultSet rs = sta.executeQuery("select * from t_book");
        //处理结果
        while(rs.next()){ //判断是否有记录存在
            System.out.println(rs.getInt("id") + "---" + rs.getString("name") + "---" + rs.getString("author") + "---" + rs.getDouble("price"));
        }

        //关闭数据库
        sta.close();
        conn.close();


    }
}
