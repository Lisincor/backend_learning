package chapter13枚举类;

public class StatusTest {
    public static void main(String[] args) {
        Employer em = new Employer("张三",25,Status.FREE);
        System.out.println(em);
    }
}

class Employer{
    private String name;
    private int age;
    private Status status;

    public Employer() {
    }

    public Employer(String name, int age, Status status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", status=" + status +
                '}';
    }
}