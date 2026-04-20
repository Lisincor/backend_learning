package chapter10多态知识点;

public class Test {
    public static void main(String[] args) {
     Test test = new Test();
        test.adopt(new Dog());
    }

    void adopt(Animal animal){ //使用父类做方法的形参
        animal.eat();
    }


}

class Animal {
    public void eat(){
        System.out.println("吃");
    }
}

class Dog extends Animal{
    int age;
    public void eat(){
        System.out.println("狗吃狗粮");
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}