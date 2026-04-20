package test02老马书城数据库版;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class 老马书城客户端 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        while (true) {
            System.out.println("------欢迎来到【老马书城】------");
            System.out.println("1.根据编号查询书籍");
            System.out.println("2.查询所有书籍");
            System.out.println("3.下架指定编号的书籍");
            System.out.println("4.退出应用");
            System.out.println("请录入功能序号：");
            //借助 Scanner类：
            Scanner sc = new Scanner(System.in);
            //利用键盘录入序号：
            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.println("请输入您查询书籍的编号");

                //录入编号
                int bno = sc.nextInt();
                //根据编号查询书籍：
                Book b = findBookByBno(bno);

                if(b == null){
                    System.out.println("书籍不在库中");
                }else{
                    System.out.println(b.getId()+"---"+b.getName());
                }

            } else if (choice == 2) {

            } else if (choice == 3) {

            } else if (choice == 4) {
                System.out.println("退出应用成功");
                break;
            }
        }
    }

        public static Book findBookByBno (int bno) throws ClassNotFoundException, SQLException {
            Book b = null;
            //加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/mikasa?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
            String username = "root";
            String password = "dontshut";
            Connection conn = DriverManager.getConnection(url, username, password);
            //创建会话
            Statement sta = conn.createStatement();
            //发送sql
            ResultSet rs = sta.executeQuery("select * from t_book where id =" + bno);
            //处理结果
            if (rs.next()) {
                //先将结接果接受
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                double price = rs.getDouble("price");

                b = new Book();
                b.setId(id);
                b.setName(name);
                b.setAuthor(author);
                b.setPrice(price);
            }

            //关闭数据库
            sta.close();
            conn.close();

            return b;
        }


}
