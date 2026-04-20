package test03封装;

public class Girl {
     private int age ; //只能在类内部访问
    private double height;
    private String name;

     
    public void setAge(int age) {
        this.age = age;//this表示当前这个age
    }

    public void setHeight(double height) {
        this.height = height;
    }
    //给age一个赋值方法

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    //设置一个读取值方法

}
