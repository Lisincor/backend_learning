package a0308;

public class TwoPointer41 {
    int valume = 0;
    public int trap(int[] height){

        //分别双指针从左和右遍历，水位不超过最高的左右两个柱子中较小的那一个；
        // 计算完某个位置的水位后，向数组中心迭代
        int len = height.length;
        int preMax = 0;
        int subMax = 0;
        int left = 0;
        int right = len - 1;

        while(left < right){

            preMax = Math.max(preMax, height[left]);
            subMax = Math.max(subMax,height[right]);

            if(preMax < subMax){
                valume+= preMax - height[left];
                left++;
            }else{
                valume += subMax - height[right];
                right--;
            }

        }

        return  valume;
    }

    public static void main(String[] args) {
        TwoPointer41 t5 = new TwoPointer41();
        int arr[] = {4,2,3};

        System.out.println(t5.trap(arr));
    }
}
