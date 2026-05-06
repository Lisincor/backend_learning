# jdbc

## 0. jdbc介绍

jdbc是接口，但并没有提供具体的实现

![image-20251002091453863](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251002091453863.png)

## 1.DriverManager

![屏幕截图(1062)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1062).png)



## 2. Connection

作用：1.获取执行sql的对象Statement

2. 管理事务

![image-20250930195829049](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20250930195829049.png)

``` java
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

```

## 3. ResultSet

```java
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

```

## 4.preparaStatement（防止sql注入

preparaStatement.set会将参数里的 ' 等字符进行转义，变成字符类型

![image-20251001145144616](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251001145144616.png)

```java
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

```



## 5.数据连接池

![image-20251001154607621](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251001154607621.png)



找不到配置文件的加载路径的时候

![image-20251001170416399](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251001170416399.png)

```java

  //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbcc-demo/src/druid.properties"));


```

实例代码：

```java
package quickstart;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;
import java.sql.Connection;

public class DruidDemo {
    public static void main(String[] args) throws Exception {

        //1.导入jar包

        //2.定义配置文件

        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbcc-demo/src/druid.properties"));

        //4.获取连接池对象
        DataSource datasource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接 Connection
        Connection connection = datasource.getConnection();

       System.out.println(connection);
    }
}

```



## 6.实战：查询和增加 （增加时需要参数

![屏幕截图(1075)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1075).png)

```java
package example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;
import pojo.Book;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TEST {

    public static void main(String[] args) throws Exception {

        //1.导入jar包

        //2.定义配置文件

        //3.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("jdbcc-demo/src/druid.properties"));

        //4.获取连接池对象
        DataSource datasource = DruidDataSourceFactory.createDataSource(prop);

        //5.获取数据库连接 Connection
        Connection connection = datasource.getConnection();

        //2.定义sql语句
        String sql = "select * from t_book";

        //3.获取psta对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //4.设置参数（查询全部不用设置参数

        //5.执行sql语句
        ResultSet rs = preparedStatement.executeQuery();

        //6.处理结果：封装为List<Book>
        Book book = new Book();
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String author = rs.getString("author");
            double price = rs.getDouble("price");


            book.setId(id);
            book.setName(name);
            book.setAuthor(author);
            book.setPrice(price);

            list.add(book);
        }
        System.out.println(list);

    }
}


```

## 7.mybatis vs jdbc

![image-20251002092333337](https://cdn.statically.io/gh/Lisincor/image-hosting@main//image-20251002092333337.png)

![屏幕截图(1096)](https://cdn.statically.io/gh/Lisincor/image-hosting@main//%E5%B1%8F%E5%B9%95%E6%88%AA%E5%9B%BE(1096).png)
