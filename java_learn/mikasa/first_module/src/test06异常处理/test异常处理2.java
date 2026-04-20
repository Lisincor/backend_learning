package test06异常处理;

public class test异常处理2 {
    public static void main(String[] args) {
            try {
                devide();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }

    public static void devide() throws Exception{
        int num1 = 12;
        int num2 = 0;
        if(num2 == 0){
            // try {
            //     throw new Exception();
            // } catch (Exception e) {
            //     // TODO: handle exception
            //     System.out.println("zhelideyichangwozijichulil");
            // }
            throw new  Exception(); //创造异常, throws抛出异常
        }
           else{
            System.out.println("这两个数的商是" + num1/num2);
           }

    }
    //c:\Users\19776\Pictures\java\throws与throw区别.png
}
