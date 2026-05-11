package a0308;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class array_189 {


    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;

        array_189 array_189 = new array_189();
        array_189.rotate(nums, k);
    }

    public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int[] num_fir = Arrays.copyOfRange(nums, 0, nums.length - k);
        int[] num_second = Arrays.copyOfRange(nums, nums.length - k, nums.length);

        int[] result = IntStream.concat(
                IntStream.of(num_second),
                IntStream.of(num_fir)
        ).toArray();

        //从数组层面复制，不改变数组引用指向
        System.arraycopy(result,0,nums,0,result.length);
    }
}
