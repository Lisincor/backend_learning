package chapter04;

public class java01_object {
    public static void main(String[] args){
     Cooking c = new Cooking();
     c.food="鲫鱼";
     c.name="清蒸鱼";
     c.excute();
    }
}

class Cooking{
    String food;
    String name;


    void excute(){
        System.out.println("开始烹饪"+food);
        System.out.println(name+"烹饪结束");
    }
}