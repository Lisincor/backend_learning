package chapter03Thread;

public class Test02 {
    public static void main(String[] args) {
   //3.创建实现类的子对象
        Even e = new Even();

    //4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的实例
        Thread t1 = new Thread(e);
        t1.start();


        for(int i = 0 ; i < 100; i++){
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }


        //方式3：
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0 ; i < 100; i++){
                    if(i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }

            }
        }).start();
    }
}


class Even implements Runnable{

    @Override
    public void run() {
        for(int i = 0 ; i < 100; i++){
            if(i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}