package com.fangxiaobin.concurrency.chapter6;

/**
 * @author 方晓彬 2020/6/21 23:38
 * 优雅的停止线程
 * 使用interrupt 打断线程方法停止线程
 **/
public class ThreadCloseGreceful2 {

    private static class Worker extends Thread{
        @Override
        public void run() {
            while (true){
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                    break;//return
//                }


                if(Thread.interrupted()){
                    break;
                }
                System.out.println("线程被中断时，执行其他事情");


            }


        }
    }

    public static void main(String[] args) {

        Worker worker = new Worker();
        worker.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.interrupt();

    }


}
