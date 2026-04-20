package test;

public class 方法 {
    //提取一个方法，将两个数求和
    public static int addNum(int num1, int num2){
        int sum = num1 + num2 ;
        return sum;
    }
    public static void addNum(int num1,int num2,int num3){
        System.out.println(num1+num2+num3);
    }
    public static void main(String[] args) {
        int number = addNum(10,20);
        System.out.println(number);
        addNum(29,20,21);
    }
}