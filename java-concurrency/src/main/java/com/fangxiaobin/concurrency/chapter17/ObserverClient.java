package com.fangxiaobin.concurrency.chapter17;

/**
 * @author 方晓彬 2020/7/8 1:22
 **/
public class ObserverClient {

    public static void main(String[] args) {
        final Subject subject = new Subject();

        //通过聚合的方式 通知
        new BinaryObserver(subject);
        new OctalObserver(subject);

        System.out.println("---------------");
        subject.setState(10);

        System.out.println("《---------------》");
        subject.setState(12);

        System.out.println("《---------------》");
        subject.setState(16);


    }

}
