package com.fangxiaobin.concurrency.chapter10;

/**
 * @author 方晓彬 2020/6/29 23:23
 *
 * synchronized 线程不能打断机制 解决技巧
 **/
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {

        new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        }.start();

        Thread.sleep(1000_0);

        Thread t2 = new Thread(){
            @Override
            public void run() {
                SynchronizedProblem.run();
            }
        };
        t2.start();
        Thread.sleep(2000);
        //打断
        t2.interrupt();
        System.out.println(t2.isInterrupted());

    }

    private synchronized static void run() {

        System.out.println(Thread.currentThread().getName());
//        while (true){
//            Thread.sleep(100_000);
//        }
    }

}
