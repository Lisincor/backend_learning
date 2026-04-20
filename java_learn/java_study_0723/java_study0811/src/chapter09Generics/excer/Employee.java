package chapter09Generics.excer;

public class Employee implements Comparable<Employee>{
    private String name;
    private int age;
    private Mydate birthday;

    public Employee() {
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

    public Mydate getBirthday() {
        return birthday;
    }

    public void setBirthday(Mydate birthday) {
        this.birthday = birthday;
    }

    public Employee(String name, int age, Mydate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    @Override
    public int compareTo(Employee o) {
        if(o == this) return 0;


        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
