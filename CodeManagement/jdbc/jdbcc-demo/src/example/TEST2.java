package example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import pojo.Book;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TEST2 {

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
        Double price = 19.2;
        String name = "精灵";
        String author = "江南";
        String sql = "insert into t_book(name,author,price) values(?,?,?)";

        //3.获取psta对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //4.设置参数
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,author);
        preparedStatement.setDouble(3,price);

        //5.执行sql语句
        int count = preparedStatement.executeUpdate();

        //6.处理结果：封装为List<Book>
        System.out.println(count > 0);

    }
}

