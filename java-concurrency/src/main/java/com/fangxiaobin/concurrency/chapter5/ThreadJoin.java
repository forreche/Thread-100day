package com.fangxiaobin.concurrency.chapter5;

import java.util.stream.IntStream;

/**
 * @author 方晓彬 2020/6/20 23:03
 **/
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            IntStream.range(1,1000).
                    forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
        });

        Thread t2 = new Thread(() ->{
            IntStream.range(1,1000).
                    forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));
        });
        t1.start();
        t2.start();


        //等待当前线程结束 再执行 相对于main线程来说
        //就是 使用join时  main线程需要 等待join的线程结束后才能执行 main线程是他t1 t2的启动线程
        t1.join();
        t2.join();


        IntStream.range(1,1000).
                forEach(i-> System.out.println(Thread.currentThread().getName()+"->"+i));

    }
}
