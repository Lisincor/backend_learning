package chapter04;

public class java08_final {
    public static void main(String[] args) {
     test t = new test("lio");
    }
}
class test{
    public final String name;
    test(String name){
        this.name = name;
    }
}