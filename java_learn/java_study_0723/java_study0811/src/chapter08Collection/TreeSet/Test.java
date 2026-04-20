package chapter08Collection.TreeSet;

import chapter06Date.Te;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Test {

    /**
     * 1.String类已经实现了自然排序
     *
     */


    @org.junit.Test
    public void test01(){
     TreeSet treeSet = new TreeSet();

     treeSet.add("OOO");
     treeSet.add("PPP");
     treeSet.add("RRR");

     Iterator iterator = treeSet.iterator();
     while(iterator.hasNext()){

         System.out.println(iterator.next());
     }
 }

 @org.junit.Test
    public void test02(){
     TreeSet treeSet = new TreeSet();

     User u1 = new User("Tom",29);
     User u2 = new User("Kevin",19);
     User u3 = new User("Vector",39);
     User u4 = new User("Lom",9);
     User u5 = new User("Pom",29);


     treeSet.add(u1);
     treeSet.add(u2);
     treeSet.add(u3);
     treeSet.add(u4);
     treeSet.add(u5);

     for (Object o :treeSet) {
         System.out.println(o);
     }


 }

 @org.junit.Test
    public void test03(){
     Scanner sc = new Scanner(System.in);
     int u = sc.nextInt();
     System.out.println(u);
 }
 



}
