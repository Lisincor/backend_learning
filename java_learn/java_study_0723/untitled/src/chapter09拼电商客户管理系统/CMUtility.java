package chapter09拼电商客户管理系统;


import java.util.Scanner;

public class CMUtility {


     public static char readMenuSelection() {
         Scanner sc = new Scanner(System.in);
         char cc = sc.next().charAt(0);

         while (cc != '1' && cc != '2' && cc != '3' && cc != '4' && cc != '5'){
             System.out.println("输入错误，请输入正确的数字：");
             cc = sc.next().charAt(0);
         }

             return cc;
     }

     public static char readConfirmSelection(){

         Scanner sca = new Scanner(System.in);
         char c = sca.next().charAt(0);

         while(c!='Y' && c != 'N'){
             System.out.println("输入错误，请输入正确的操作指令：");
             c = sca.next().charAt(0);
         }

             return c;
     }

}
