package com.fangxiaobin.concurrency.chapter11;

/**
 * @author 方晓彬 2020/6/30 22:20
 **/
public class ThreadException {

    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(5_000L);
                int result = A/B;
                System.out.println(result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        // thread 的内部方法  FunctionalInterface 记录捕获了什么异常 异常的线程
        t.setUncaughtExceptionHandler((thread,e) -> {
            System.out.println(e);
            System.out.println(thread);
        });
        t.start();
    }

}
