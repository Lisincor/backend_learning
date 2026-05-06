package a0308;

public class TwoPointer4 {

    int volume = 0;

    public int trap(int[] height) {
        int len = height.length;


        for(int i = 0; i< len ;){

            boolean flag = true;
            int j = i + 1;
            for (; j < len; j++){
                if(height[j] >= height[i]){
                    flag = false;
                    break;
                }
            }

            if(!flag){
                    caculate(i,j,height); //计算雨水的普通流程
                    i = j;
            }else{ //后面的都是低于的height[i] 柱子的高度，从下一个算
                i++;
            }
        }

        return volume;
    }

    public void caculate(int i, int j,int height[]){
        int iFlag = i;
        i++;
        while(i < j){
            volume += (height[iFlag] - height[i]);
            i++;
        }
    }

    public static void main(String[] args) {
        TwoPointer4 t4 = new TwoPointer4();
        int a[] = {4,2,0,3,2,5};

        int trap = t4.trap(a);
        System.out.println(trap);

    }
}
