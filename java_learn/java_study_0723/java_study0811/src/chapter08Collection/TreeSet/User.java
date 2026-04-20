package chapter08Collection.TreeSet;

import java.util.Objects;

public class User implements Comparable{
    String name;
    int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
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

    /*
    年龄从小到大

     */
    @Override
    public int compareTo(Object o) {
        if( o == this) return 0;

        if( o instanceof  User){
            User u = (User) o;
            int value = this.age - u.age;
            if(value != 0){
                return value;
            }

            return this.name.compareTo(u.name);

        }

        throw new RuntimeException("类型不匹配");
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
