package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 22:35
 *
 * 单例模式下的多线程
 *
 * 单例模式主要解决 懒加载
 **/
public class SingletonObject1 {

    /**
     * can`t lazy load;
     * 不能懒加载
     */
    private static final SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1(){
        //empty

    }


    public static SingletonObject1 getInstance(){
        return instance;
    }


}
