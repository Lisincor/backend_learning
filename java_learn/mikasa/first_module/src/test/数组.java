package test;public class 数组 {
    public static void main(String[] args) {
        //数组声明
        int[] arr;
        //数组创建
         arr = new int[4]; // 默认值为0

         int[] arr2 = new int[4];
         //数组赋值
        arr[0] = 12;
        arr[1] = 2912;

        //数组遍历
        for(int num : arr){
            System.out.println(num);
        }
    }
}
