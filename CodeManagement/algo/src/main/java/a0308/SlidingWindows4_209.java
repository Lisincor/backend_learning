package a0308;

public class SlidingWindows4_209 {
    public int minSubArrayLen(int target, int[] nums) {

        int left = 0, right = 0;
        int ansleft = -1, ansright = nums.length;
        int sum = 0;

        for(; right < nums.length; right++){
            sum += nums[right];

            while(sum >= target){
                if(right - left < ansright - ansleft) {
                    ansleft = left;
                    ansright = right;
                }
                sum -= nums[left];
                left++;
            }
        }


        return ansleft == -1 ? 0 : ansright - ansleft + 1;
    }
}
