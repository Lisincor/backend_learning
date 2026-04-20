package chapter15Throwable;

import org.junit.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchFinally {


    public static void main(String[] args) {

        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入数据");
            int num = scanner.nextInt();
            System.out.println(num);
        }catch(InputMismatchException e){
            System.out.println("出现了 InputMismatchException");
        }catch (NullPointerException e){
            System.out.println("出现了空指针异常");
        }catch(RuntimeException e){ //如果多个异常满足子父类关系，子类必须写在父类上面
            e.printStackTrace();
        }

        //catch处理异常方式：
        //1.自己打印信息
        //2.使用方法：printStackTrace() --打印异常的详细信息
        //           getMessage() --


        System.out.println("异常处理结束");
    }
}
