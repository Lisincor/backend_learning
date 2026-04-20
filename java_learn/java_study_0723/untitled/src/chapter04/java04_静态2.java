package chapter04;

public class java04_静态2 {
    public static void main(String[] args) {
       CCC c = new CCC();
        System.out.println(c.i);
       //TODO  先有类，再有对象
        //成员方法可以访问静态属性和静态方法的
        //静态方法不能够访问成员方法
       /* Test t = new Test();
        t.test();
        t.test1();*/


        //类的信息加载完成后，会自动调用静态代码块
        //对象创建时，不管静态代码块还是代码块都会被执行
        User3.test();
        System.out.println("下一个");
        new User3();
    }
}
class Test{

    String name;
    static String sex;
    void test(){
        test1(); // 有成员方法时，类的信息(及静态方法一定是存在的)
        System.out.println("test");
    }

    static void test1(){
        System.out.println("test1");
    }
}
class bird{
    static void fly(){
        System.out.println("飞飞飞");
    }
    static String name = "鸟";
}

// 静态代码块
class User3{
    static{
   //静态代码块执行
        System.out.println("静态代码块1");
    }
    static  void test(){

    }
    {
        System.out.println("代码块1");
    }

}