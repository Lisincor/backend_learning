package chapterDeBug;

public class Test {
    public static void main(String[] args) {
        Kid kid = new Kid();

        kid.setAge(1);
        kid.setName("胡歌");

        System.out.println("我爱玩原神");
        System.out.println(kid.getAge());
    }
}
