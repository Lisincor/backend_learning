package chapter_practiceCustomExceptions;

public class Test {
    public static void main(String[] args) {
        Person p1 = new Person();
        try {
           p1.setName("李星");
           p1.setLifeValue(123);
        }catch (NoLifeValueException e){
            e.printStackTrace();
        }
        System.out.println(p1);
    }
}
