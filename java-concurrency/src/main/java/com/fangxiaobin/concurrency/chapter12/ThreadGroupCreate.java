package com.fangxiaobin.concurrency.chapter12;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.Arrays;

/**
 * @author 方晓彬 2020/7/1 0:09
 * <p>
 * ThreadGroup API
 **/
public class ThreadGroupCreate {

    /**
     * main方法是一个线程  是由jdk创建的线程 它的ThreadGroup 是 MainThreadGroup
     */
    public static void main(String[] args) {

        //Thread.currentThread表示当前代码段正在被哪个线程调用的相关信息。
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());


        ThreadGroup tg1 = new ThreadGroup("TG1");
        Thread t1 = new Thread(tg1, "T1") {
            @Override
            public void run() {
                try {

                    //获取线程组的名字
//                    System.out.println(getThreadGroup().getName());
                    //获取线程组的父线程组
//                    System.out.println(getThreadGroup().getParent());
                    //线程sleep的时候不会放弃cpu执行权
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();


        //没有给父线程组的情况
//        ThreadGroup tg2 = new ThreadGroup("TG2");
        //将tg1 线程组当作当前线程组的父线程组
        //两种情况下获取的活跃线程数不同
        ThreadGroup tg2 = new ThreadGroup(tg1, "TG2");
//        System.out.println(tg2.getName());
//        System.out.println(tg2.getParent());
        Thread t2 = new Thread(tg2, "T2") {
            @Override
            public void run() {
                System.out.println(">>>" + tg1.getName());

                //获取 tg1线程组的活跃线程数 添加到线程数组里
                Thread[] threads = new Thread[tg1.activeCount()];

                // 在这个线程组及其子组中的每一个活动线程中拷贝到指定的数组中。
                tg1.enumerate(threads);
                Arrays.asList(threads).forEach(System.out::println);
            }
        };

        t2.start();

        //获取线程组的活跃线程
        System.out.println(tg1.activeCount());
        //获取线程组中活跃的线程组
        System.out.println(tg1.activeGroupCount());

        //获取是否可以更改
        t2.checkAccess();

    }


}
