package chapter09拼电商客户管理系统;

import java.util.Scanner;

public class CustomerView {
    CustomerList customerlist = new CustomerList(10);

    public void enterMainMenu(){

        boolean is = true;

        while(is){
            //显示界面
            System.out.println("\n----------------拼电商客户管理系统---------------\n");
            System.out.println("\t\t\t\t\t1.添加客户");
            System.out.println("\t\t\t\t\t2.修改客户");
            System.out.println("\t\t\t\t\t3.删除客户");
            System.out.println("\t\t\t\t\t4.客户列表");
            System.out.println("\t\t\t\t\t5.退出界面");
            System.out.print("\t\t\t\t\t请选择(1-5):");

            char key = CMUtility.readMenuSelection();
            switch (key){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleCustomer();
                    break;
                case '4':
                    listAllCustomer();
                    break;
                case '5':
                    System.out.print("是否要确认退出(Y/N):");
                    char flag = CMUtility.readConfirmSelection();
                    if(flag == 'Y')  is = false;
                    break;
            }

        }
    }


    public static void main(String[] args) {
        CustomerView view = new CustomerView();

        view.enterMainMenu();
    }

    private void addNewCustomer(){

        System.out.println("\n----------------填写客户信息---------------\n");

        Scanner sc1 = new Scanner(System.in);

        System.out.print("姓名：");
        String name = sc1.next();
        System.out.print("性别：");
        char gender = sc1.next().charAt(0);
        System.out.print("年龄：");
        int age = sc1.nextInt();
        System.out.print("手机号：");
        String phone = sc1.next();
        System.out.print("邮箱：");
        String email = sc1.next();

        Customer cus = new Customer(name,gender,age,phone,email);

        if(customerlist.addCustomer(cus) ){
            System.out.println("添加成功");
        }else{
            System.out.println("客户已满");
        }

    }

    private void modifyCustomer(){

    }

    private void deleCustomer(){

    }

    private void listAllCustomer(){

    }


}
