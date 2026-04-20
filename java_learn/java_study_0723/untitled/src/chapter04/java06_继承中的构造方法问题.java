package chapter04;

public class java06_继承中的构造方法问题 {
    public static void main(String[] args) {
        //父类对象是在子类创造前创建完成，创建子类对象前，会调用父类的构造方法完成父类的创建
        //如果父类提供构造方法，那么JVM不会提供默认的构造方法，那么子类应该显示调用super方法构建对象
      Child c = new Child();
    }
}

class Parent{
    String username;
    Parent(String name){
        username = name;
        System.out.println("我爱玩原审");
    }
}

class Child extends Parent{

    Child(){
        super("zhangsan"); // 当父类构造方法中有有参数时，就不再用无参数的构造器了
        System.out.println(" 我爱玩mc");
    }
}