package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/19 23:41
 **/
public class BankVersion2 {

    private final static int MAX = 50;

    public static void main(String[] args) {
//        final TicketWindowRunnable ticketWindowRunnable = new TicketWindowRunnable();
//        Thread windowThread1 = new Thread(ticketWindowRunnable,"一号窗口");
//        Thread windowThread2 = new Thread(ticketWindowRunnable,"二号窗口");
//        Thread windowThread3 = new Thread(ticketWindowRunnable,"三号窗口");
//
//
//        windowThread1.start();
//        windowThread2.start();
//        windowThread3.start();



        final Runnable ticketWindow =() ->{
            int index = 1;

            while (index <= MAX) {
                System.out.println("柜台："+Thread.currentThread()+"当前号码是：" + (index++));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        ticketWindow.run();

    }

}
