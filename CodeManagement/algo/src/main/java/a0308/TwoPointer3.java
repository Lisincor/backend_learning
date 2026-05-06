package a0308;

import java.util.*;

public class TwoPointer3 {
    public  List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> listNum = new ArrayList<>();
        Set<List<Integer>> setNum = new HashSet<>();

        Arrays.sort(nums);


        int len = nums.length;
        for(int i = 0; i < len - 2; i++ ){

            int p1 = i + 1;
            int p2 = len -1;
            int count = -nums[i];

            while(p1 < p2){

                if(nums[p1] + nums[p2] < count){
                    p1++;
                } else if (nums[p1] + nums[p2] > count) {
                    p2--;
                }else{
                    setNum.add(new ArrayList<>(Arrays.asList(nums[i],nums[p1],nums[p2])));
                    break;
                }
            }

        }

        setNum.stream().forEach(e -> listNum.add(e));

        return listNum;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};

        TwoPointer3 t = new TwoPointer3();
        List<List<Integer>> lists = t.threeSum(nums);


    }
}
