package a0308;

public class array_238 {
    class Solution {
        public int[] productExceptSelf(int[] nums) {

            int pre[] = new int[nums.length+1];
            int suffix[] = new int[nums.length+1];

            pre[0] = 1;
            suffix[0] = 1;

            pre[1] = nums[0];
            suffix[1] = nums[nums.length - 1];

            for(int i = 1; i <= nums.length;i ++){
                pre[i] = pre[i-1] * nums[i-1];
                suffix[i] = suffix[i-1] * nums[nums.length-i];
            }

            int[] result = new int[nums.length];
            for(int i = 0;i < nums.length; i++){
                int j = i + 1;
                result[i] = pre[j-1] * suffix[nums.length - j];
            }

            return result;
        }
    }
}
