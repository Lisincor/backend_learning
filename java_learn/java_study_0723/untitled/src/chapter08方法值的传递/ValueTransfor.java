package chapter08方法值的传递;

public class ValueTransfor {
    public static void main(String[] args) {
        ValueTransfor va = new ValueTransfor();
        int m = 10;
        va.menthod1(m);
        System.out.println(m);

        Person P = new Person();
        P.age=10;
        va.menthod2(P);
        System.out.println(P.age);

    }

    public  void menthod1(int m){
        m++;
        System.out.println(m);
    }

    public void menthod2(Person p){
       p.age++;
    }
}

class Person{
    int age;
}