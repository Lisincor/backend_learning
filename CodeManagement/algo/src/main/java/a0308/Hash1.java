package a0308;

import java.util.HashMap;
import java.util.Map;

public class Hash1 {

    int[] nums;
    int target;

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;

        for(int i = 0;i < len; i++){
                int last = target - nums[i];
                if(map.containsKey(last)){
                    return new int[]{map.get(last),i};
                }
            map.put(nums[i],i);
        }
        return new int[0];
    }
}
