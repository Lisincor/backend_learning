package a0308;

import java.util.ArrayList;

public class SlidingWindow3_560 {

        public int[] maxSlidingWindow(int[] nums, int k) {

           int[] qlist = new int[100010];
           ArrayList<Integer> res = new ArrayList<>();

           int hh = 0,tt=-1;
           for(int i = 0;i < nums.length;  i++){

               if( hh <= tt &&  i-k+1 > qlist[hh] )  hh++;

               while( hh <= tt && nums[i] >= nums[qlist[tt]]) tt--;

               qlist[++tt] = i;

               if(i-k + 1>= 0) res.add(nums[qlist[hh]]);
            }

            return res.stream().mapToInt(Integer::intValue).toArray();
        }

}
