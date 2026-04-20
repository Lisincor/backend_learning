package chapter04;

public class java03_静态 {
    public static void main(String[] args) {
        Bird.fly();
    }
}

class Bird{
    static void fly(){
        System.out.println("飞飞飞");
    }
    static String name = "鸟";
}
