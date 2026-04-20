package chapter02throw;

public class ThrowTest {
    public static void main(String[] args) {

        Student s1 = new Student();
        try {
            s1.regist(-10);
            System.out.println(s1);
        }catch(RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}

class Student{
    int id;

    public void regist(int id){
        if(id > 0){
            this.id = id;
        }else{
            throw new RuntimeException("输入的iddd非法");//此时是运行时异常，不用处理也行
        }
    }
}
