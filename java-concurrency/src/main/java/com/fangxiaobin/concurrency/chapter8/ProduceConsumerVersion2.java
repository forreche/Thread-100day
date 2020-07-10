package com.fangxiaobin.concurrency.chapter8;

import java.util.stream.Stream;

/**
 * @author 方晓彬 2020/6/25 22:13
 * <p>
 * <p>
 * 线程间通信
 **/
public class ProduceConsumerVersion2 {

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
            if (isProduced) {
                try {
                    //等待
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("p->" + i);
                LOCK.notify();
                isProduced = true;
                //TODO
            }
        }

    }


    /**
     * 消费
     */
    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                System.out.println("C>---" + i);

                //唤醒
                LOCK.notify();

                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        ProduceConsumerVersion2 p = new ProduceConsumerVersion2();


/*        new Thread(){
            @Override
            public void run() {
                while (true){

                    p.produce();
                }
            }
        }.start();


        new Thread(){
            @Override
            public void run() {
                while (true){

                    p.consume();
                }
            }
        }.start();*/


        Stream.of("p1", "p2").forEach(n -> {
            //在多个生产者消费者情况下出问题
            new Thread() {
                @Override
                public void run() {
                    while (true) {

                        p.produce();
                    }
                }
            }.start();
        });

        Stream.of("c1", "c2").forEach(n -> {
            new Thread() {
                @Override
                public void run() {
                    while (true) {

                        p.consume();
                    }
                }
            }.start();
        });


    }


}
