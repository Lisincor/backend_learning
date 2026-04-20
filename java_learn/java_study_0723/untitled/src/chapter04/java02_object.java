package chapter04;

public class java02_object {
    public static void main(String[] args) {
        USER  user = new USER();
        user.test(10);
        user.test(10,"yuasns"); // 存在其他变量的时候，可变参数放在最后
    }
}

class USER{
    void test(int a,String...name){
        System.out.println(name);
    }
}