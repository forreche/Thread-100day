package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/19 2:33
 *
 * 银行类 多线程叫号
 **/
public class Bank {

    public static void main(String[] args) {

        TicketWindow ticketWindow = new TicketWindow("一号柜台");
        ticketWindow.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号柜台");
        ticketWindow2.start();


        TicketWindow ticketWindow3 = new TicketWindow("三号柜台");
        ticketWindow3.start();

    }
}
