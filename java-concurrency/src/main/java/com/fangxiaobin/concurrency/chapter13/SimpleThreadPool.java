package com.fangxiaobin.concurrency.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author 方晓彬 2020/7/1 20:57
 *
 * 手写一个基础的线程池
 *
 * 初始化大小 活跃大小 任务队列
 **/
public class SimpleThreadPool
{

    /**
     * 初始化大小
     */
    private final int size;

    /**
     * 默认大小
     */
    private final static int DEFAULT_SIZE = 10;

    /**
     * 任务队列
     */
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();


    private static volatile int seq = 0;


    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");

    private final List<WorkerTask> THREAD_QUEUE = new ArrayList<>();


    public SimpleThreadPool(){
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size){
        this.size = size;
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        for (int i = 0; i < size; i++) {
            createWorkTask();
        }
    }


    /**
     * 提供接口
     */
    public void submit(Runnable runnable){
        synchronized (TASK_QUEUE){
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }


    /**
     * 构建任务队列
     */
    private void createWorkTask(){
        WorkerTask task = new WorkerTask(GROUP,THREAD_PREFIX+(seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }


    /**
     * 状态
     */
    private enum TaskState{
        FREE,RUNNING,BLICKED,DEAD
    }

    private static class WorkerTask extends Thread{
        private volatile TaskState taskState = TaskState.FREE;


        public WorkerTask(ThreadGroup group,String name){
            super(group,name);
        }

        public TaskState getTaskState(){
            return this.taskState;
        }


        /**
         * 线程停止后不能立刻消亡
         */
        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD){
                Runnable runnable;
                synchronized (TASK_QUEUE){
                    while (TASK_QUEUE.isEmpty()){
                        try {
                            taskState = TaskState.BLICKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if(runnable != null){
                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }


            }
        }


        /**
         * 关闭
         */
        public void close(){
            this.taskState = TaskState.DEAD;
        }






    }


    public static void main(String[] args) {
        SimpleThreadPool threadPool = new SimpleThreadPool();
        IntStream.rangeClosed(0,40)
                .forEach(i ->
                    threadPool.submit(()->{
                        System.out.println("The runnable "+ i+ "be serviced by "+ Thread.currentThread() +"start");

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println("The runnable "+i+"be serviced by "+Thread.currentThread() + "end");

                    })
                );
    }

}
