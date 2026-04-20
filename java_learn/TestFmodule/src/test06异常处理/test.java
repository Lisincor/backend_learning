package test06异常处理;

public class test {
    public static void main(String[] args) {
        try {
            int num1= 11;
            int num2 = 11;
            System.out.println(+ num1/num2);
        } catch (Exception ex) {

            System.out.println("对不起，程序出错了");
            // TODO: handle exception
        }finally{
            System.out.println("无论程序是否异常，这个逻辑都会执行");
        }
    }
} //c:\Users\19776\Pictures\java\try-catch执行情况.png
