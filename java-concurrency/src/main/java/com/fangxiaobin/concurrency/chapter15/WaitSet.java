package com.fangxiaobin.concurrency.chapter15;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @author 方晓彬 2020/7/4 0:18
 * <p>
 * <p>
 * 多线程休息室 WaitSet
 * <p>
 * 1.去哪唤醒线程
 * 2.怎么唤醒
 * 3.唤醒后怎么运行
 **/
public class WaitSet {

    /**
     * 1.所有的对象都会有一个wait set ，用来存放调用了该对象 wait 方法之后进入block 状态线程
     * 2.线程被notify之后 不一定立即得到执行
     * 3.线程从wait set 中被唤醒 顺序不一定是FIFO
     * 4.线程被唤醒后需要重新获取锁
     */
    private static final Object LOCK = new Object();



    private static void work(){
        synchronized (LOCK){
            System.out.println("Begin....");
            try {
                System.out.println("Thread will coming");
                //wait需要抢锁 但是不需要重新执行 因为锁会记录 即程序计数器会记录位置 wait 被唤醒后会从当前位置继续执行
                LOCK.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread will out");
        }
    }

    public static void main(String[] args) throws InterruptedException {

//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            new Thread(String.valueOf(i)) {
//                @Override
//                public void run() {
//                    synchronized (LOCK) {
//                        try {
//                            Optional.of(Thread.currentThread().getName() + " will come to wait set.").ifPresent(System.out::println);
//                            LOCK.wait();
//                            Optional.of(Thread.currentThread().getName() + " will leave to wait set.").ifPresent(System.out::println);
//
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }.start();
//        });


        new Thread(){
            @Override
            public void run() {
                work();
            }
        }.start();
        Thread.sleep(1000);
        synchronized (LOCK){
            LOCK.notify();
        }

//        Thread.sleep(3000);
//
//        IntStream.rangeClosed(1, 10).forEach(i -> {
//            synchronized (LOCK){
//                LOCK.notify();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

}
