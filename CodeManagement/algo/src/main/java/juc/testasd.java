package juc;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j(topic="c.Test3")
public class testasd {

    static Object lock = new Object();

    static int count = 0;

    public static void main(String[] args) {
        Thread thread = new Thread( () -> log.debug("nihao"));
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {

                    synchronized (lock){
                        count++;
                    }

                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    log.debug("asdasdasd");
                }
            }
        };

        Thread thread1 = new Thread(r1,"线程2");

        thread.start();
        thread1.start();
        log.debug("aa");
    }
}
