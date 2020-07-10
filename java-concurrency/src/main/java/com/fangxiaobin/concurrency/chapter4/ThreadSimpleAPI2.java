package com.fangxiaobin.concurrency.chapter4;

import java.util.Optional;

/**
 * @author 方晓彬 2020/6/20 22:55
 **/
public class ThreadSimpleAPI2 {

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                Optional.of(Thread.currentThread().getName()+"-index"+i).ifPresent(System.out::println);
            }
        });


        //设置线程优先级
        t3.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t1.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();


    }





}
