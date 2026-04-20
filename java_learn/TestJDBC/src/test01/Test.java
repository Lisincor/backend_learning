package test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
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
        //发送sql
        int i = sta.executeUpdate("delete from t_book where id=2;");
        //处理结果
        if(i > 0){//证明对数据库的数据条数有影响
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }

        //关闭数据库
        sta.close();
        conn.close();


    }
}
