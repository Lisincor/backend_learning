package chapter06Date;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Date;

public class Te {
    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test1(){
        Date date1 = new Date();//创建一个当前系统时间的计数器
        System.out.println(date1.toString());

        long mills = date1.getTime();
        System.out.println("当前的毫秒数" + mills);

        Date date2 = new Date(1670212988380L);//创建一个指定时间戳的Data实例
        System.out.println(date2);
    }

    @Test
    public void test2(){
        LocalTime localTime = LocalTime.now();

        System.out.println(localTime);
    }

}
