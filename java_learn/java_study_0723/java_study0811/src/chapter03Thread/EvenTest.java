package chapter03Thread;

public class EvenTest {
    public static void main(String[] args) {
        EvenNumberPriter t1 = new EvenNumberPriter();
        t1.start();
    }

}

class EvenNumberPriter extends Thread{

    public EvenNumberPriter() {
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i ++){
            if(i %2==0){
                System.out.println(i);
            }
        }
    }
}