package chapter14包装类;

public class StringSwitchBaseandWrapper {

    public static void main(String[] args) {

        //方式1：调用String的重载的静态方法valueOf(xxx)
        int i1 = 10;
        String str1 = String.valueOf(i1);
        System.out.println(str1);

        boolean b1 = true;
        Boolean b2 = b1;
        String str2 = String.valueOf(b1);
        String str3 = String.valueOf(b2);

        System.out.println(b1);
        System.out.println(b2);

        //方式2：基本数据类型变量 + ""
        String str4 =  i1 + "";
        String str5 =  b1+"";
        System.out.println(str4);
        System.out.println(str5);

        // String类型  ----> 基本数据类型，包装类: 调用包装类的静态方法： parseXxx()
        String s1 = "123";
        int i2 = Integer.parseInt(s1);
        System.out.println(i2+10);

        String s2 = "true";
        boolean b3 = Boolean.parseBoolean(s2);

        String s3 = "123a";
        int i3 = Integer.parseInt(s3); //报错

    }

}
