package a0308;

import java.util.Arrays;

public class TwoPointer1 {
    public void moveZeroes(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        int len = nums.length;
        for(int i = 0;i < len;i++){
            if(nums[i] == 0 & i + 1 < len){
                p1 = i;
                p2 = i + 1;
                while(p2 < len ){
                    if(nums[p2] != 0)
                        break;

                    p2++;
                }

                if(p2 < len){
                    nums[p1] = nums[p2];
                    nums[p2] = 0;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }

    public static void main(String[] args) {
        int[] nums = {0,1,0,3,12};
        System.out.println(Arrays.toString(nums));
        TwoPointer1 t = new TwoPointer1();
        t.moveZeroes(nums);
    }
}
