package chapter06Scanner;

import java.util.Scanner;

public class scanner_learn {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String name = scan.next();
        System.out.println(name);

        scan.close();
    }
}
