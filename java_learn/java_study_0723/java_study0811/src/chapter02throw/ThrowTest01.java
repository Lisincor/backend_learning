package chapter02throw;

public class ThrowTest01 {
    public static void main(String[] args) {

        Person s1 = new Person();
         try {
             s1.regist(-10);
             System.out.println(s1);
         }catch (Exception e) {
             e.printStackTrace();
         }
    }
}

class Person{
    int id;

    public void regist(int id) throws Exception{
        if(id > 0){
            this.id = id;
        }else{
            throw new Exception("输入的idd非法");
        }
    }
}
