package chapter08Collection.基本使用;

import org.junit.Test;

import java.util.*;

public class BasicTest {
  @Test
    public void test1(){
      List<Integer> list = new ArrayList<>();


      //list.add("o");//编译报错，保证了程序的安全
      list.add(102);

      Iterator<Integer> iterator = list.iterator();
      while(iterator.hasNext()){
          Integer i = iterator.next();
          int score = i;
          System.out.println(score);
      }
  }

  @Test
  public void test2(){
      HashMap<String,Integer> hashMap = new HashMap<>();

      hashMap.put("Pork",12);
      hashMap.put("Tok",122);
      hashMap.put("Jark",12);

      Set<Map.Entry<String, Integer>> entrySet = hashMap.entrySet();
      Iterator<Map.Entry<String, Integer>> iterator = entrySet.iterator();

      while(iterator.hasNext()){
          System.out.println(iterator.next());
      }
  }


}
