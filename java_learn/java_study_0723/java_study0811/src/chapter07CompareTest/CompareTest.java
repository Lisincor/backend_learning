package chapter07CompareTest;

import java.util.Arrays;

public class CompareTest {
    public static void main(String[] args) {
    Product p[] = new Product[3];
    p[0] = new Product("aaa",1300.0);
    p[1] = new Product("bbb",1100.0);
    p[2] = new Product("ccc",1200.0);

    Arrays.sort(p);

    for(int i = 0; i < p.length; i ++){
        System.out.println(p[i].toString());
    }

    }
}
