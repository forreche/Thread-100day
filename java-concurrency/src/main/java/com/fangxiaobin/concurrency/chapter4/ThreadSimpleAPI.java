package com.fangxiaobin.concurrency.chapter4;

import java.util.Optional;

/**
 * @author 方晓彬 2020/6/20 22:46
 **/
public class ThreadSimpleAPI {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");
        t.start();
        Optional.of(t.getName()).ifPresent(System.out::println);
        Optional.of(t.getId()).ifPresent(System.out::println);
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
