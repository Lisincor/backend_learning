package test02;

public class TEST {
    public static void main(String[] args) {
        //对person类的对象进行创建，创建一个person对象，名字叫p1

        person p1= new person();
        //对属性赋值：
        p1.name = "网戈";
        p1.age = 21;

        System.out.println(p1.name);
        System.out.println(p1.age);
         p1.study();
    }
}
