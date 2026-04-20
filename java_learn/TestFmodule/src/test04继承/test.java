package test04继承;

public class test {
    public static void main(String[] args) {
        Student s = new Student();

        s.setSno(123);
        s.setAge(19);
        s.setName("王加");

        System.out.println(s.getName());
        s.eat();
        s.sleep();
        s.study();
    }
}
