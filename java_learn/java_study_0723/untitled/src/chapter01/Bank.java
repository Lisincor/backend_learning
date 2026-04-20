package chapter01;

public class Bank {
    //1.构造器私有化
    private Bank(){

    }

    //2.在类的内部创建当前类的实例
    //4.类也变为静态
    private static Bank bank = new Bank();

    //3.使用getXXX()方法获取实例
    public static Bank instance(){
        return bank;
    }

}
