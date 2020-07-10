package com.fangxiaobin.concurrency.chapter9;

import java.util.*;

/**
 * @author 方晓彬 2020/6/28 21:08
 *
 * 线程生产者消费者的综合实战
 *
 * 多线程采集数据
 *
 **/
public class CaptureService {

   final static private LinkedList<Control> CONTROL = new LinkedList<>();

   private final static int MAX_WORKER = 5;

    public static void main(String[] args) {

        List<Thread> worker = new ArrayList<>();
        Arrays.asList("M1","M2","M3","M4","M5","M6","M7","M8","M9","M10").stream()
                .map(CaptureService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    worker.add(t);
                });

        worker.stream().forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);
    }


    /**
     * 用于创建线程 定义规定量的线程
     * @param name
     * @return
     */
    private static Thread createCaptureThread(String name){
        return new Thread(()->{
            Optional.of("The worker ["+Thread.currentThread().getName()+"] begin capture data.").ifPresent(System.out::println);

            synchronized (CONTROL){

                //如果 线程队列中 已经存在了规定量的线程 则停止加入
                while (CONTROL.size() > MAX_WORKER){
                    try {
                        CONTROL.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //没有则加入线程队列
                CONTROL.add(new Control());
            }

            //线程开始工作
            Optional.of("The worker ["+Thread.currentThread().getName()+"] is working .....").ifPresent(System.out::println);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 线程工作结束后 退出线程队列 通知其他线程工作
            synchronized (CONTROL){
                Optional.of("The worker ["+Thread.currentThread().getName()+"] is END capture data .....").ifPresent(System.out::println);
                CONTROL.removeFirst();
                CONTROL.notifyAll();
            }

        },name);
    }



    private static class Control{}

}
