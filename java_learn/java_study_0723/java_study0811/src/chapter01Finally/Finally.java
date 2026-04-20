package chapter01Finally;

public class Finally {
    public static void main(String[] args) {
     int i = test("ooo");
        System.out.println(i);
    }

    public static int test(String str){
        try{
          Integer.parseInt(str);
          return 1;
        }catch (NumberFormatException e){
            return -1;
        }finally {
            System.out.println(
           "万元神"
            );
        }

    }

}
