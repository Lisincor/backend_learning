package a0308;

import java.util.HashSet;
import java.util.Set;



public class hash3 {
    public static void main(String[] args) {

    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> numSet = new HashSet<>();

        for(int num : nums){
            numSet.add(num);
        }
        int streak = 0;
        for(int num : numSet){
            if(!numSet.contains(num-1)){
                int streakFake = 1;

                while(numSet.contains(num+1)){
                    num++;
                    streakFake++;
                }
                streak = Math.max(streakFake,streak);
            }
        }
        return  streak;
    }
}
