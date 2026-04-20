package chapter04;

public class java07_多态难点 {
    public static void main(String[] args) {
        //一个对象能用什么方法，取决于引用变量的类型
        //一个对象能用什么属性，取决于引用变量的类型
        //一个对象的具体的方法的使用是看具体的对象,如果子类里面有，那么执行子类的方法
        //一个对象属性的具体使用不需要看具体的对象，在哪里声明在哪里使用
        CCC ccc= new DDD();
        System.out.println(ccc.get());
    }
}

class CCC{
    protected int i = 25;
    int sum(){
        return get() + 20;
    }

    int get(){
        return i;
    }
}

class DDD extends CCC{
    int i = 10;
  /*  int sum(){
        return i + 10;
    }*/

//    int get(){
//        return i;
//    }
}
