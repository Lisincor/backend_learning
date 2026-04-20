package chapter07CompareTest;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorTest {
    public static void main(String[] args) {
        Product p[] = new Product[3];
        p[0] = new Product("aaa",1300.0);
        p[1] = new Product("bbb",1100.0);
        p[2] = new Product("ccc",1200.0);

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof Product && o2 instanceof Product){

                    Product p1 = (Product) o1;
                    Product p2 = (Product) o2;

                    return Double.compare(p1.price, p2.price);
                }

                throw new RuntimeException("类型不匹配");
            }
        };

        Arrays.sort(p,comparator);

        for(int i = 0; i < p.length; i ++){
            System.out.println(p[i].toString());
        }

    }
}
