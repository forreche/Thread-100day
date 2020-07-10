package com.fangxiaobin.concurrency.chapter10;

import java.util.Collection;

/**
 * @author 方晓彬 2020/6/28 22:08
 *
 * 自定义显示锁
 * 解决sychronized 机制问题
 *
 **/
public interface Lock {

    class TimeOutException extends Exception{

        public TimeOutException(String message){
            super(message);
        }

    }


    /**
     * 允许中断
     * @throws InterruptedException
     */
    void lock() throws InterruptedException;


    /**
     * 在规定时间内没有获取到锁 直接抛出异常 退出
     * @param mills
     * @throws InterruptedException
     * @throws TimeOutException
     */
    void lock(long mills) throws InterruptedException,TimeOutException;


    /**
     * 释放锁
     */
    void unlock();


    /**
     * 观察有多少线程在抢锁时阻塞住
     * @return
     */
    Collection<Thread> getBlockedThread();


    /**
     *
     * @return
     */
    int getBlockedSize();


}
