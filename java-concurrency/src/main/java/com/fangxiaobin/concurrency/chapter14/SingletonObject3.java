package com.fangxiaobin.concurrency.chapter14;

/**
 * @author 方晓彬 2020/7/3 22:42
 **/
public class SingletonObject3 {

    private static SingletonObject3 instance;

    private SingletonObject3(){

    }


    /**
     * 加锁后 能够解决创建多个实例 即实现单例模式 并且是懒加载
     * 但是 串行化 。
     * 加锁会导致运行慢。
     * @return
     */
    public synchronized static SingletonObject3 getInstance(){
        if(null == instance){
            instance = new SingletonObject3();
        }
        return SingletonObject3.instance;
    }

}
