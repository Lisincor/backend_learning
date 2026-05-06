package juc;

import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;
import static java.util.concurrent.locks.LockSupport.park;
import static java.util.concurrent.locks.LockSupport.unpark;

@Slf4j
public class Test2 {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() ->
        {
            log.debug("开始执行");
            park();
            log.debug("结束执行");
        },"线程1");

        thread1.start();

        new Thread(() ->{
            log.debug("开始执行");
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            unpark(thread1);
            log.debug("结束执行");
        },"线程2").start();
    }
}
