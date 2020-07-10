package com.fangxiaobin.concurrency.chapter16;

/**
 * @author 方晓彬 2020/7/4 0:51
 * volatile关键字
 * 共享数据 感知数据变化
 * 内存可见性 有序性
 *
 **/
public class VolatileTest {

    private volatile static int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {

                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }

            }
        }, "READER").start();


        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value updated to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();

    }

}
