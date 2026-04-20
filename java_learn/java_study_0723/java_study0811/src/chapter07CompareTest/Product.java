package chapter07CompareTest;

public class Product implements Comparable{
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }


    @Override
    public int compareTo(Object o) {
        if(o == this) return 0; //相等就是0

        if(o instanceof Product){
            Product p = (Product)o;

            return Double.compare(this.price,p.price);//大于0就是大于，小于0就是小于
        }

        throw new RuntimeException("类型不匹配");
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
