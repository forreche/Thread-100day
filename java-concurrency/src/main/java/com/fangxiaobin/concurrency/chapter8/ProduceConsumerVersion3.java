package com.fangxiaobin.concurrency.chapter8;

import java.util.stream.Stream;

/**
 * @author 方晓彬 2020/6/26 0:14
 * <p>
 * 多个生产者消费者情况
 **/
public class ProduceConsumerVersion3 {

    private int i = 0;

    final private Object LOCK = new Object();


    /**
     * 是否已经生产标记
     */
    private volatile boolean isProduced = false;


    /**
     * 生产
     */
    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    //等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            i++;
            System.out.println("生产者"+Thread.currentThread().getName()+"正在执行---->" + i);
            isProduced = true;
            LOCK.notifyAll();
            //TODO
        }

    }


    /**
     * 消费
     */
    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者"+Thread.currentThread().getName()+"正在消费------>" + i);
            //唤醒
            isProduced = false;
            LOCK.notifyAll();
        }
    }


    public static void main(String[] args) {
        ProduceConsumerVersion3 p = new ProduceConsumerVersion3();

        Stream.of("p1", "p2","p3", "p4", "p5", "p6").forEach(n -> {
            //在多个生产者消费者情况下出问题
            new Thread(n.intern()) {
                @Override
                public void run() {
                    while (true) {
                        p.produce();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        Stream.of("c1", "c2","c3", "c4", "c5", "c6").forEach(n -> {
            new Thread(n.intern()) {
                @Override
                public void run() {
                    while (true) {

                        p.consume();

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }.start();
        });


    }


}
