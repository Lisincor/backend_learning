package test07集合;

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        //增加
        list.add("aaa");
        list.add("ass");
        list.add("元素");
        System.out.println(list);

        //删除
         list.remove("aaa");
         System.out.println(list);

         //修改
         list.set(0,"eee");//修改下标为0的元素
         System.out.println(list);

          //查看元素
          System.out.println(list.get(1));
           
          //遍历
          for(int i = 0; i < list.size(); i ++){
          System.out.println(list.get(i));
          }

    }
}
