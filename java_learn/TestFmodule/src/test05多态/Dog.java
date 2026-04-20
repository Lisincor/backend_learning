package test05多态;

public class Dog extends Animal{
    public void shout(){//子类的shout方法对父类的方法不满意，方法的重写
        System.out.println("汪汪叫");
    }
    public void kanjia(){
        System.out.println("看家");
    }
}
