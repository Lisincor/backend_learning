package mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pojo.Book;

import java.util.List;

@Mapper//运行时，会自动生成该接口的实现类对象，并且将该对象交给IOC容器管理
public interface BookMapper {

    @Select("select * from t_book")
    public List<Book> findAll();
}