package chapter07数组.a01;

public class 二维数组 {
    public static void main(String[] args) {

        //1. 初始化

        //静态初始化1
        int[][] arr2 = new int[][]{{1,2,3},{2,3}};

        //动态初始化
        String[][] arr3 = new String[3][4];
        String[][] arr4 = new String[3][];


        //其他正确的写法
        int arr5[][] = new int[4][];
    }
}
