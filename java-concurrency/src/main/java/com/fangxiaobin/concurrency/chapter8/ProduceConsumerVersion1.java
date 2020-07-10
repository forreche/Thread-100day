package com.fangxiaobin.concurrency.chapter8;

/**
 * @author 方晓彬 2020/6/25 22:04
 *
 * 多线程间通信
 *
 * 生产者 消费者模式
 *
 * 运行结果不符合需求
 * 线程间没有通信
 *
 **/
public class ProduceConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    private void produce(){
        synchronized (LOCK){
            System.out.println("p->" + (i++));
        }
    }


    private void consume(){
        synchronized (LOCK){
            System.out.println("c-> "+ i);
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 ps = new ProduceConsumerVersion1();
        new Thread("p"){
            @Override
            public void run() {
                while (true){

                    ps.produce();
                }
            }
        }.start();

        new Thread("c"){
            @Override
            public void run() {
               while (true){

                   ps.consume();
               }
            }
        }.start();

    }


}
