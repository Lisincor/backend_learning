package chapter12接口;

public class Test {
    public static void main(String[] args) {
        System.out.println(Flyable.maxSpeed);

        Plane p = new Plane();
        p.Fly();
    }
}

interface Flyable{
    public static final int minSpeed = 0;
    int maxSpeed = 7900;

    void Fly();
}

class Plane implements Flyable{

    @Override
    public void Fly() {
        System.out.println("飞机飞");
    }


}