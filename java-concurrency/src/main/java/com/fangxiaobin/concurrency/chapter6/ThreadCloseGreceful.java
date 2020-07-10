package com.fangxiaobin.concurrency.chapter6;

/**
 * @author 方晓彬 2020/6/21 23:33
 * 优雅的停止线程
 * 1.通过开关的方式
 **/
public class ThreadCloseGreceful {

    private static class Worker extends Thread{
        //开关
        private volatile boolean start = true;
        @Override
        public void run() {
            while (start){

            }
        }
        public void shutdown(){
            this.start = false;
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
        worker.shutdown();
    }

}
