package chapter13枚举类;

public class Test {
    public static void main(String[] args) {
        System.out.println(Season.SPRING);
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SUMMER.getDesc());

        //Enum常用方法
        //1.toString
        System.out.println(Season.SPRING);

        //2.name()
        System.out.println(Season.SPRING.name());

        //3.values()
        Season[] values = Season.values();
        for(int i = 0; i < values.length; i++){
            System.out.println(values[i]);
        }

        //4.valueOf()
    }
}

enum Season{

    // 实例之间用逗号隔开
    SPRING("春天","春暖花开"),
    SUMMER("夏天","夏日炎炎"),
    AUTUMN("秋天","丰收季节"),
    WINTER("冬天","冷冽刺骨");

    private Season (String name,String desc){
        this.name = name;
        this.desc = desc;
    }

    String name;
    String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}