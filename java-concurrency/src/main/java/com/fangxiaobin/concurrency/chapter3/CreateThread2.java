package com.fangxiaobin.concurrency.chapter3;

import java.util.Arrays;

/**
 * @author 方晓彬 2020/6/20 1:01
 **/
public class CreateThread2 {


    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
        t.getThreadGroup();
        System.out.println(t.getThreadGroup());
        System.out.println(Thread.currentThread().getName());


        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        //获取线程组中的活动线程
        System.out.println(threadGroup.activeCount());



        //
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        //等于下面的遍历 java8新特性
        Arrays.asList(threads).forEach(System.out::println);


        for (Thread temp: threads
             ) {
            System.out.println(temp);
        }

    }
}
