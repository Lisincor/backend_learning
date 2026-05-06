package a0308;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SlidingWindow2 {

        public List<Integer> findAnagrams(String s, String p) {

            //初始化数据结构和指针
            List<Integer> listNum = new ArrayList<>();
            HashMap<Character, Integer> mapOrigin = new HashMap<>();  //用来计算的HashMap
            HashMap<Character, Integer> mapVerfiy = new HashMap<>(); //p的hashmap
            for (int i = 0; i < p.length(); i++) {
                mapOrigin.put(p.charAt(i), mapOrigin.getOrDefault(p.charAt(i), 0) + 1);
            }
            mapVerfiy.putAll(mapOrigin);
            int len = s.length();
            int left = 0;
            int right = 0;

            //运算过程
            for (; left <= right && right < len; right++){
                char c = s.charAt(right);

                //不包括直接跳到最前面
                if (!mapOrigin.containsKey(c)){
                    left = right + 1;
                    mapOrigin.putAll(mapVerfiy);
                    continue;
                }

                int num = mapOrigin.get(c) - 1; // 占用一个值
                mapOrigin.put(c, num);

                // 如果num小于0，说明窗口中c的数量大于p中c的数量，left右移到第一个c的右边
                if(num < 0)
                    for(;left<=right;left++){
                        mapOrigin.put(s.charAt(left), mapOrigin.get(s.charAt(left))+1); //left移动，释放占有的字符的数量
                        if(s.charAt(left) == c) {
                            left = left + 1;
                            break;
                        }
                    }
                // 成立条件，如果窗口大小等于p的长度，则说明找到了一个子串，将left加入到list中
                if (right - left + 1 == p.length()) {
                    listNum.add(left);
                    mapOrigin.put(s.charAt(left),mapOrigin.get(s.charAt(left))+1);
                    left++;
                }
            }
            return listNum ;
        }


    public static void main(String[] args) {
        SlidingWindow2 s2 = new SlidingWindow2();
        String s = "abaacbabc";
        String p = "abc";

        System.out.println(s2.findAnagrams(s, p));
    }
}
