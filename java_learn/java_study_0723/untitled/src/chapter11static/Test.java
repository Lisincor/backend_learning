package chapter11static;

public class Test {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println(c1);
        Circle c2 = new Circle();
        System.out.println(c2);

        System.out.println(Circle.init);
    }
}

class Circle{
    int num;
    double radius;

    public Circle(){
        this.num = init;
        init++;
    }

    static int init = 90;

    @Override
    public String toString() {
        return "Circle{" +
                "num=" + num +
                ", radius=" + radius +
                '}';
    }
}