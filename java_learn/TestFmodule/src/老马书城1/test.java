package 老马书城1;

import java.util.Scanner;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
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
      for(int i = 0; i < list.size(); i ++){
        book b = (book)(list.get(i));
        System.out.println(b.getbNo() + "---" + b.getbName() + "---" + b.getbAuthor());
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
         
         // 添加在集合中
         list.add(b);
        }else if( choice == 3){
         System.out.println("【老马书城】>>>>>>3.下架书籍");
         //录入你要下架的书籍
         System.out.println("请输入你要下架书籍的编号");
         int delNo = sc.nextInt();

         for(int i = 0; i < list.size(); i ++){
            book b = (book)list.get(i);
            if(delNo == b.getbNo()) {
          list.remove(b);
          System.out.println("书籍下架成功");
          break;  
            }
         }

        }else if(choice == 4){
            System.out.println("退出应用成功");
            break;
        }
    }
    }
}
