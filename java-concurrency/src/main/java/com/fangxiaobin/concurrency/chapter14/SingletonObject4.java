package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 22:46
 *
 * 解决多线程模式下 单例模式 并且懒加载 问题
 * 不加锁 使用双重检查
 *
 * 单例 懒加载 运行速率
 * 但是会存在空指针异常
 *
 **/
public class SingletonObject4 {


    private static SingletonObject4 instance;

    private SingletonObject4(){
        //empty

    }

    /**
     * double check
     */
    public static SingletonObject4 getInstance(){
        //先判断 是否等于空
        if (null == instance){
            //抢锁
            synchronized (SingletonObject4.class){
                if(null==instance){
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }


}
