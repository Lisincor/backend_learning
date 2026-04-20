package test09创建线程;
/*
 * 2.创建一个线程类： TestThread
 * 3.想要具备多线程能力
 */

public class TestThread extends Thread{
    //4.线程对应的任务放在一个方法；
    @Override
    public void run() {
        // TODO Auto-generated method stub
       //线程任务：输出10个数
       for(int i = 1; i <= 10;i ++){
        System.out.println(i);
       }
    }
}
