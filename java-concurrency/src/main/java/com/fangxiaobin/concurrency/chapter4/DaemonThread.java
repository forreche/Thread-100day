package com.fangxiaobin.concurrency.chapter4;

/**
 * @author 方晓彬 2020/6/20 22:36
 **/
public class DaemonThread {


    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"----");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName()+"---------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
        //设置为守护线程 不会随着main线程停掉而停掉
//        t.setDaemon(true);

        //runnable ---> running --> |dead| --> blocaked

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(Thread.currentThread().getName());
    }


}
