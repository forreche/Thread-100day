package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/19 23:39
 **/
public class TicketWindowRunnable implements Runnable{

    private int index = 1;

    private final static int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台："+Thread.currentThread()+"当前号码是：" + (index++));
        }
    }
}
