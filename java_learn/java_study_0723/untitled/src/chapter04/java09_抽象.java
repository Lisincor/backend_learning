package chapter04;

public class java09_抽象 {
    public static void main(String[] args) {
   Chinese c = new Chinese();
   c.eat();
    }
}

abstract class Person{
    public abstract void eat();
    public void test() {}
}

class Chinese extends Person{
    public void eat(){
        System.out.println("中国人吃饭");
    }
}