package a0308;

public class SubString1 {


    public int subarraySum(int[] nums, int k) {

        //初始化
        int count = 0;
        int[] preSum = new int[nums.length + 1];
        preSum[0] = 0;
        for(int i = 1; i <= nums.length; i++){
            preSum[i] = preSum[i - 1] + nums[i-1];
        }

        // 遍历
        for(int i = 0; i < nums.length; i++)
            for(int j = i; j < nums.length; j++)
                if(preSum[j + 1] - preSum[i] == k)
                    count++;

        return count;
    }
}
