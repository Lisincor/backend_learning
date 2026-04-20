package chapter05;

public class Enum {
    public static void main(String[] args) {
     //枚举是一个特殊的类，其中包含了一组特定的对象，这些对象不会发生改变，一般都使用大写的标识符
     //枚举类会将对象放置在最前面，那么后面的语法需要使用分号隔开
     //
        System.out.println(City.BEIJING.code);
        System.out.println(City.SHANGHAI.name);
        System.out.println(MyCity.BEIJING.code);
    }
}

class MyCity{
    private MyCity(String name, int code){
        this.name = name;
        this.code = code;
    }

    //接收传过来的属性值
    public String name;
    public int code;

    public static final MyCity BEIJING = new MyCity("北京",1001);
    public static final MyCity SHANGHAI = new MyCity("上海",1002);


}



enum City{
    BEIJING("北京",10001),SHANGHAI("上海",10002); //都是City的对象

    //构造方法赋值
    City(String name, int code){
   this.name = name;
   this.code = code;
    }

    //接收传过来的属性值
    public String name;
    public int code;

}