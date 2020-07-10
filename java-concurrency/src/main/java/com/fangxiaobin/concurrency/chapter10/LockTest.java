package com.fangxiaobin.concurrency.chapter10;

import com.fangxiaobin.concurrency.chapter6.ThreadService;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author 方晓彬 2020/6/28 23:50
 **/
public class LockTest {

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("T1","T2","T3","T4")
                .forEach(name ->
                        new Thread(() -> {
                            try {
                                booleanLock.lock(3L);
                                Optional.of(Thread.currentThread().getName()+" have the lock monitor..").ifPresent(System.out::println);
                                work();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (Lock.TimeOutException e) {
                                Optional.of(Thread.currentThread().getName() + "time out").ifPresent(System.out::println);
                                e.printStackTrace();
                            } finally {
                                booleanLock.unlock();
                            }
                        },name).start());

    }


    //抢锁工作
    private static void work() throws  InterruptedException{
        Optional.of(Thread.currentThread().getName() + "is Working...").ifPresent(System.out::println);
        Thread.sleep(10_000);
    }



}
