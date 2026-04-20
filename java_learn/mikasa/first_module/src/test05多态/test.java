package test05多态;

public class test {
    public static void main(String[] args){
      //创建小女孩和猫的实例
      Girl g = new Girl();
//      Cat c = new Cat();
//      Dog d = new Dog();
//      g.play(d);

      Animal an = new Dog();// 父类引用 an 指向 子类对象 new Dog()
      g.play(an); //表面上操作 父类对象an，实际上操作Dog的对象
        // Girl: play -> 具体的an的类
        
        //an.kanjia(); 子类扩展的方法用不了
    }
}
