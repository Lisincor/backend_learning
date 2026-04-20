package test02;



/*
  人类
 */
public class person {
    //特性 - 属性 - 名词（只定义与业务逻辑相关的代码）
    String name;
    int age;
    double height;


    //行为 - 方法

    public void study(){
        System.out.println("我爱中国");
    }

    public person(){
        System.out.println("调用");
    }

    public person(int age,String name, double height){
        this.age = age;
        this.name = name;
        this.height = height;
    }
}
