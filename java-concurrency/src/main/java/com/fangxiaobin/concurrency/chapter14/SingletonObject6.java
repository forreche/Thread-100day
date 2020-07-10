package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 23:01
 *
 * 优雅的解决多线程的单例模式 在双重检查情况下的空指针异常
 **/
public class SingletonObject6 {

    private SingletonObject6(){

    }

    /**
     * 内部类
     */
    private static class InstanceHolder{
        private final static SingletonObject6 instance = new SingletonObject6();
    }

    public static SingletonObject6 getInstance(){
        return InstanceHolder.instance;
    }

}
