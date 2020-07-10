package com.fangxiaobin.concurrency.chapter7;

/**
 * @author 方晓彬 2020/6/25 21:46
 *
 * 死锁演示
 **/
public class DeadLock {

    private OtherService otherService;

    DeadLock(OtherService otherService){
        this.otherService = otherService;
    }

    private final Object lock = new Object();

    public void m1(){
        synchronized (lock){
            System.out.println("m1");
            otherService.s1();
        }
    }


    public void m2(){
        synchronized (lock){
            System.out.println("m2");
        }
    }
}
