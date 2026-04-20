package chapter07数组.a01;

import java.util.Scanner;

public class 数组练习 {
    public static void main(String[] args) {
        String[] week = new String[7];
        week[0] = "Monday";
        week[1] = "Tuesday";
        week[3] = "Thursday";
        week[2] = "Wednesday";
        week[4] = "Friday";
        week[5] = "Saturday";
        week[6] = "Sunday";

        Scanner sc = new Scanner(System.in);

        for(int i = 0;i < 9;i++){
        int tem = sc.nextInt();
        tem--;
            System.out.println(week[tem]);
        }

        sc.close();
    }
}
