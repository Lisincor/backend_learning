package 老马书城2IO流版;

import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
       
        while(true){
        System.out.println("------欢迎来到【老马书城】------");
        System.out.println("1.展示书籍");
        System.out.println("2.上新书籍");
        System.out.println("3.下架书籍");
        System.out.println("4.退出应用");
        System.out.println("请录入功能序号：");
        //借助 Scanner类：
        Scanner sc = new Scanner(System.in);
        //利用键盘录入序号：
        int choice =  sc.nextInt();
        
        if(choice == 1){

          //从文件中读取list:
          File f = new File("d:\\bengtie.txt");

          if(f.exists() == true){
          //流
          FileInputStream fis = new FileInputStream(f);
          ObjectInputStream ois = new ObjectInputStream(fis);
          
          //读取集合
          ArrayList list = (ArrayList)(ois.readObject());//list从文件中读取的集合
          if(list.size() != 0) {
      for(int i = 0; i < list.size(); i ++){
        book b = (book)(list.get(i));
        System.out.println(b.getbNo() + "---" + b.getbName() + "---" + b.getbAuthor());
      }
    }else{
       System.out.println("当前还未上新书籍");
    }
    }else{
      System.out.println("当前还未上新书籍");
    }

        }else if(choice == 2){
        System.out.println("【老马书籍】>>>>>>>2.上新书籍");

        //从键盘录入信息
        System.out.println("请录入书籍编号：");
        int bNo = sc.nextInt();

        System.out.println("请输入书籍名字");
        String bName = sc.next();

        System.out.println("请输入书籍作者");
        String bAuthor = sc.next();

        //每上新一本书，创建一本书籍对象
         book b = new book();
         b.setbNo(bNo);
         b.setbName(bName);
         b.setbAuthor(bAuthor);
         
          //从文件中读取list:
          File f = new File("d:\\bengtie.txt");

          //先判断文件是否存在
          if(f.exists() == true){
          //流
          FileInputStream fis = new FileInputStream(f);
          ObjectInputStream ois = new ObjectInputStream(fis);
          
          //读取集合
          ArrayList list = (ArrayList)(ois.readObject());//list从文件中读取的集合
          

          //集合读取出来以后，再增加新的书籍
          list.add(b);

          //再将集合的内容写出去
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
         
        oos.close();

    }else{ //如果文件不存在，那么是第一次上新书籍

      // 添加在集合中
          ArrayList list = new ArrayList();
         list.add(b);
      
        //流; 管套管
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
         
        oos.close();

    }



         
        }else if( choice == 3 ){
         System.out.println("【老马书城】>>>>>>3.下架书籍");

          System.out.println("请输入你要下架书籍的编号");

         File f = new File("d:\\bengtie.txt");

         if(f.exists() == true){
         //录入你要下架的书籍
        
          //流
          FileInputStream fis = new FileInputStream(f);
          ObjectInputStream ois = new ObjectInputStream(fis);
          
          //读取集合
          ArrayList list = (ArrayList)(ois.readObject());//list从文件中读取的集合

          //删除操作
         int delNo = sc.nextInt();
        
         if(list.size() != 0){
         for(int i = 0; i < list.size(); i ++){
            book b = (book)list.get(i);
            if(delNo == b.getbNo()) {
          list.remove(b);
          System.out.println("书籍下架成功");
          break;  
            }
         }

             //再将集合的内容写出去
        FileOutputStream fos = new FileOutputStream(f);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
         
        oos.close();
         }else{
  System.out.println("当前书库中并未有书");
         }
        }else{
          System.out.println("当前书库中并未有书");
        }
        }else if(choice == 4){
            System.out.println("退出应用成功");
            break;
        }
    }
    }
}
