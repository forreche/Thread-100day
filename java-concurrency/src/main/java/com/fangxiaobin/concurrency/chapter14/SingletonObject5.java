package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 22:57
 *
 *
 * 解决多线程的单例模式 在双重检查情况下的空指针异常
 **/
public class SingletonObject5 {

    /**
     * 添加上 volatile
     *
     * volatile 保证原子性
     * 就是告诉编译器不要优化
     */
    private static volatile SingletonObject5 instance;

    private SingletonObject5(){
        //empty

    }

    /**
     * double check
     */
    public static SingletonObject5 getInstance(){
        //先判断 是否等于空
        if (null == instance){
            //抢锁
            synchronized (SingletonObject5.class){
                if(null==instance){
                    instance = new SingletonObject5();
                }
            }
        }
        return SingletonObject5.instance;
    }


}
