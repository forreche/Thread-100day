package com.fangxiaobin.concurrency.chapter5;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author 方晓彬 2020/6/20 23:12
 **/
public class ThreadJoin2 {


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->{
            try {
                System.out.println("t1 正在执行");
                Thread.sleep(1000);
                System.out.println("t1 已经结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        //main线程在等待 规定的时间后开始执行任务
        t1.join(100,10);


        Optional.of("All of tasks finish done.").ifPresent(System.out::println);
        IntStream.range(1,1000)
                .forEach(i->System.out.println(Thread.currentThread().getName()+"->"+i));




    }
}
