package com.fangxiaobin.concurrency.chapter3;

/**
 * @author 方晓彬 2020/6/20 0:41
 *
 * Thread API
 **/
public class CreateThread {

    public static void main(String[] args) {

        //Thread()
        Thread t = new Thread();
        t.start();
        System.out.println(t.getName());



        //Thread(Runnable runnable)
        Thread t2 = new Thread(){
            @Override
            public void run() {
                System.out.println("--------");
            }
        };

        t2.start();


        //Thread(String name)
        Thread t3 = new Thread("myname");
        System.out.println(t3.getName());


        //Thread(Runnable runnable)
        Thread t4 = new Thread(()->{
            System.out.println("-->");
        });
        System.out.println(t4.getName());


        //Thread(Runnable runnable,String name)
        Thread t5 = new Thread(()->{
            System.out.println("Runnable...." + Thread.currentThread().getName());
        },"RunnableThread");
        t5.start();





    }
}
