package a0308;

public class hash4_76 {
    public String minWindow(String S, String T) {
        //hash映射字符
        int[] cntS = new int[128];
        int[] cntT = new int[128];

        for(char c : T.toCharArray()){
            cntT[c]++;
        }

        char s[] = S.toCharArray();
        int len = s.length;
        // ansleft, ansright 记录最小窗口的左右边界，并不断更新
        // left, right 记录当前窗口的左右边界
        int left = 0, right = 0;
        int ansleft = -1, ansright = len;

        for(; right < len; right++){
            cntS[s[right]]++;

            while(check(cntS, cntT)){ //迭代
                if(right - left < ansright - ansleft){
                    ansleft = left;
                    ansright = right;
                }
                cntS[s[left]]--;
                left++;
            }
        }


        return  ansleft > ansright ? "" : S.substring(ansleft, ansright + 1);
    }

    private boolean check(int[] cntS, int[] cntT) {
        for(int i = 'a'; i <= 'z'; i++){
            if(cntS[i] < cntT[i])
                return false;
        }

        for (int i = 'A'; i <= 'Z'; i++){
            if(cntS[i] < cntT[i])
                return false;
        }

        return true;
    }
}