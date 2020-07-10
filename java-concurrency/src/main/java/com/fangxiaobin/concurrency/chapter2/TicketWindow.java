package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/19 2:31
 *
 * 叫号器
 **/
public class TicketWindow extends Thread {

    private final String name;

    /**
     * 加了static 资源共享但是  导致内存资源被占用 单独占用一份空间  生命周期长 从程序创建到程序消亡
     */
    private static final int MAX = 50;
    private static int index = 1;

    /**
     * 资源不共享情况下会出现各玩各现象
     * @param name
     */
//    private  final int MAX = 50;
//    private  int index = 1;


    public TicketWindow(String name){
        this.name = name;
    }

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println("柜台："+name+"当前号码是：" + (index++));
        }
    }
}
