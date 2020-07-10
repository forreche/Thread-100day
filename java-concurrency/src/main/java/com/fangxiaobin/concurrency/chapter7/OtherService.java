package com.fangxiaobin.concurrency.chapter7;

/**
 * @author 方晓彬 2020/6/25 21:46
 *
 * 死锁演示
 **/
public class OtherService {

    private final Object lock = new Object();

    private DeadLock deadLock;


    public void s1(){
        synchronized (lock){
            System.out.println("s1-=================");
        }
    }

    public void s2(){
        synchronized (lock){
            System.out.println("s2-=================");
            deadLock.m2();
        }
    }

    public void setDeadLock(DeadLock deadLock) {
        this.deadLock = deadLock;
    }
}
