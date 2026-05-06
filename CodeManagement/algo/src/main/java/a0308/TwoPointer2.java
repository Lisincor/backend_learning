package a0308;

public class TwoPointer2 {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int len = height.length;

        int p1 = 0;
        int p2 = len-1;

        while(p1 <= p2){
            if(height[p1] >= height[p2]){
                int sub = p2-p1;
                maxArea = Math.max(height[p2] * sub, maxArea);
                p2--;
            }else{
                maxArea = Math.max(height[p1]*(p2-p1),maxArea);
                p1++;
            }
        }
        return maxArea;
    }
}
