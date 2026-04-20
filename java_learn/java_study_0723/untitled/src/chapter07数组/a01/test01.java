package chapter07数组.a01;

import javax.xml.transform.Source;

public class test01 {
    public static void main(String[] args) {

        //1.声明数组
        //声明数组
        double[] prices;
        //数组初始化
        prices = new double[]{20.3, 39.01,90.12};

        String[] foods01;
        foods01 = new String[]{"asd", "asd"};

        //声明和初始化
        //动态初始化
        String[] foods0 = new String[5];

        //其他正确的方式
        int arr[] = new int[4];
        int[] arr1 = {1, 2, 3, 4};//类型推断
        int[] arr2;

        //2.   数组元素的调用
        //下标的方式，随机访问
        System.out.println(prices[2]);

        String[] foods = new String[6];
        foods[0] = "香菜";
        foods[1] = "芹菜";

        //3. 取数组的长度
        System.out.println(foods.length);

        //4. 一些数据类型变量的初始值
        char arr3[] = new char[4];
        if(arr3[0] == 0){
            System.out.println(1111);
        }
    }
}