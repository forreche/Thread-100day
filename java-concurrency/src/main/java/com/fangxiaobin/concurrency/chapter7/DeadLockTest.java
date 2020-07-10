package com.fangxiaobin.concurrency.chapter7;

/**
 * @author 方晓彬 2020/6/25 21:52
 *
 * 死锁演示
 **/
public class DeadLockTest {

    public static void main(String[] args) {


        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(){
            @Override
            public void run() {
                while (true){

                    deadLock.m1();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                while (true){

                    otherService.s2();
                }
            }
        }.start();


    }






}
