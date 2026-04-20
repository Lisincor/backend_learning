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

