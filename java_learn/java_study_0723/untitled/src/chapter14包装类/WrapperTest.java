package chapter14包装类;

public class WrapperTest {
    public void test1 (){
        int i1 = 10;
        Integer ii1 = new Integer(10);

        double d = 1.21;
        Double dd1 = new Double(1.21);
    }


    public static void main(String[] args) {
        //1.
        int i1 = 10;
        Integer ii1 = new Integer(10);


        double d = 1.21;
        Double dd1 = new Double(d);

        
        
        //2.

        int i2 = 21;
        Integer ii2 = Integer.valueOf(i2) ;



        //包装类转换
        Double dd2 =Double.valueOf(1.232);
        double d2 = dd2.doubleValue();
        System.out.println(d2);


        Integer ii3 = i1;
        int i3 = ii3;

        System.out.println(ii3);
        System.out.println(i3);


    }
}
