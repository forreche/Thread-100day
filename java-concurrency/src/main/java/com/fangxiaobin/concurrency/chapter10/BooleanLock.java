package com.fangxiaobin.concurrency.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author 方晓彬 2020/6/28 22:28
 **/
public class BooleanLock implements Lock {

    //The initValue is true indicated the lock have be get
    //The initValue is false indicated the lock is free (other thread can get this .)
    private boolean initValue;

    /**
     *     记录阻塞状态的线程
     */
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();


    /**
     * 规定锁是谁的 谁来释放
     */
    private Thread currentThread;



    public BooleanLock(){
        this.initValue = false;
    }



    @Override
    public synchronized void lock() throws InterruptedException {

        //如果 是true
        while (initValue){
            //加入阻塞对象
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        //否则 设置成true 告诉别人我在用 并且从阻塞队列中删除
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();

    }




    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {

        if(mills <= 0){
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue){
            if(hasRemaining<= 0){
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = System.currentTimeMillis() - endTime;
        }
        this.initValue = true;


    }



    @Override
    public synchronized void unlock() {
        if(Thread.currentThread() == currentThread){
            this.initValue = false;
            System.out.println(Thread.currentThread()+"release the lock monitor..");
            this.notifyAll();
        }
    }


    /**
     * 不加锁时 可以用 但是不安全
     * @return
     */
    @Override
    public Collection<Thread> getBlockedThread() {
        //解决线程不安全 collections.unmodifiableCollection 不允许修改 修改就报错
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
