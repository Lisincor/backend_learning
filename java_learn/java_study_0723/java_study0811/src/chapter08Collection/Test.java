package chapter08Collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class Test{
    public static void main(String[] args) {

        Collection co = new ArrayList();

        co.add("我手机");
        co.add(12);//自动装箱
        System.out.println(co);

        Collection co1 = new ArrayList();
        co1.addAll(co);
        co1.add("hello");

        System.out.println(co1);


        //isEmpty()
        System.out.println(co.isEmpty());

        //contains(Object o)
        System.out.println(co.contains("hello"));
        System.out.println(new String("hello"));
        System.out.println();
    }
}
