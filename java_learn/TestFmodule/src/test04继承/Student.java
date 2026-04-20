package test04继承;

public class Student extends Person{ //
    //定义子类额外的属性
    private int sno;
    //定义子类额外的，扩展的方法

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public void study(){
        System.out.println("学习");
    }

}
