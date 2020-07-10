package com.fangxiaobin.concurrency.chapter6;

/**
 * @author 方晓彬 2020/6/21 23:09
 * <p>
 * 打断线程
 **/
public class ThreadInterrupt {

    private static final Object MONITOR = new Object();

    public static void main(String[] args) {


        //调用实例方法
/*        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            System.out.println("收到打断信号");
//                            System.out.println(">>"+this.isInterrupted());
//                            e.printStackTrace();
//                        }


                    synchronized (MONITOR) {
                        try {
                            MONITOR.wait(10);
                        } catch (InterruptedException e) {
                            System.out.println("收到打断信号");
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(t1.isInterrupted());
        t1.interrupt();
        System.out.println(t1.isInterrupted());*/


        //调用静态类方法 interrupted（）
        Thread t = new Thread(() -> {
            while (true) {
                synchronized (MONITOR) {
                    try {
                        MONITOR.wait(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        System.out.println(Thread.interrupted());
                    }
                }
            }
        });
        t.start();


        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.interrupt();
        });


        t2.start();


        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}