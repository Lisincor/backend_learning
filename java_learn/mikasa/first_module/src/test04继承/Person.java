package test04继承;


public class Person {
    //父类公共的属性
    private int age;
    private String name;
    private  double height;


    //父类公共的方法
   
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }


    public void eat(){
        System.out.println("吃饭");
    }

    public  void sleep(){
        System.out.println("睡觉");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
