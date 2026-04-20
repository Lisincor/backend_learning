package chapter01;

import chapter09拼电商客户管理系统.Customer;

import java.util.Scanner;

public class CMUtility {
    public static void main(String[] args) {
        Customer cus = new Customer();
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        Scanner sc3 = new Scanner(System.in);
        Scanner sc4 = new Scanner(System.in);
        Scanner sc5 = new Scanner(System.in);

        String name = sc1.next();
        char gender = sc1.next().charAt(0);
        int age = sc1.nextInt();
        String phone = sc1.next();
        String email = sc1.next();

        System.out.println(name +' ' + gender + age + phone + email );
    }
}
