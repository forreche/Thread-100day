package com.fangxiaobin.concurrency.chapter5;

import com.fangxiaobin.concurrency.chapter2.CalcuatorStrategy;

/**
 * @author 方晓彬 2020/6/20 23:23
 * join 方法的使用
 **/
public class ThreadJoin3 {

    public static void main(String[] args) throws InterruptedException {

        long starTimestamp = System.currentTimeMillis();

        Thread t1 = new Thread(new CaptureRunnable("M1", 1000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 3000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 1500L));

        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();

        long entTimestamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s ,end  timestamp is:%s\n", starTimestamp, entTimestamp);
    }
}


class CaptureRunnable implements Runnable {
    private String machineName;

    private long spendTime;

    public CaptureRunnable(String machineName, long spendTime) {
        this.machineName = machineName;
        this.spendTime = spendTime;
    }

    @Override
    public void run() {
        try {
            System.out.printf(machineName+"线程采集数据开始[%s]\n",System.currentTimeMillis());
            Thread.sleep(spendTime);
            System.out.printf(machineName + "采集数据完成[%s]并且成功\n",System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + "finish";
    }
}
