package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 22:38
 *
 * 懒加载的单例模式下 线程安全问题
 **/
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2(){

    }

    /**
     * 这样写会存在的问题：
     * 每次判断是否等空时 就创建一个
     * 不会是单例的
     * @return
     */
    public static SingletonObject2 getInstance(){
        if(null == instance){
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }



}
