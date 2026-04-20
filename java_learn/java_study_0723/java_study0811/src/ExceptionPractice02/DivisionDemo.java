package ExceptionPractice02;

import java.util.Scanner;

public class DivisionDemo {


    public static void main(String[] args) {

        int re = 0;
        try {
            int m = Integer.parseInt(args[0]);
            int n = Integer.parseInt(args[1]);

            re = divide(m,n);
            System.out.println(re);
        } catch (BelowZeroException e) {
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("数据类型不符合");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("缺少命令行参数");
        }catch (ArithmeticException e){
            System.out.println("除0");
        }

    }

    public static int divide(int m, int n) throws BelowZeroException{
        if(m < 0 || n  < 0){
            throw new BelowZeroException("输入数字不能为负数");
        }

        return m / n;
    }
}
