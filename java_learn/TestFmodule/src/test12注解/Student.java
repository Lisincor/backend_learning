package test12注解;

public class Student extends Person{
    @Override //注解；告诉下面的是方法重写；如果写错了，该注解会报错
    public void eat() {
        System.out.println("对父类方法的重写");
    }
}
