package com.fangxiaobin.concurrency.chapter13;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.IntStream;

/**
 * @author 方晓彬 2020/7/2 22:42
 * <p>
 * 手写线程池 拒绝策略
 **/
public class SimpleThreadPool2 {

    /**
     * 初始化大小
     */
    private final int size;

    /**
     * 默认大小
     */
    private final static int DEFAULT_SIZE = 10;


    /**
     * 外部任务队列大小
     */
    private final int queueSize;


    /**
     * 默认任务队列大小
     */
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;

    /**
     * 任务队列
     */
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();


    /**
     * 用于自增线程名
     */
    private static volatile int seq = 0;

    /**
     *
     */
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";

    /**
     * 默认的ThreadGroup
     */
    private final static ThreadGroup GROUP = new ThreadGroup("Pool_Group");


    /**
     * 线程队列
     */
    private final List<SimpleThreadPool2.WorkerTask> THREAD_QUEUE = new ArrayList<>();


    /**
     * 拒绝状态
     */
    private volatile boolean destroy = false;


    /**
     * 默认拒绝策略
     */
    public final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task");
    };


    private final DiscardPolicy discardPolicy;

    /**
     * 外部没有传值时 默认大小
     */
    public SimpleThreadPool2() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool2(int size, int queueSize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    /**
     * 初始化
     */
    private void init() {


        for (int i = 0; i < size; i++) {
            createWorkTask();
        }


    }


    /**
     * 提供接口
     */
    public void submit(Runnable runnable) {

        if(destroy){
            throw new IllegalStateException("The thread pool already destroy and allow  submit task.");
        }

        synchronized (TASK_QUEUE) {

            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }

            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
    }


    /**
     * 构建任务队列
     */
    private void createWorkTask() {
        SimpleThreadPool2.WorkerTask task = new SimpleThreadPool2.WorkerTask(GROUP, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);
    }


    /**
     * 停止线程池
     */
    public void shutdown() throws InterruptedException {
        //如果任务队列空了
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }

        int initVal = THREAD_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : THREAD_QUEUE) {
                if(task.getTaskState() == TaskState.BLICKED){
                    task.interrupt();
                    task.close();
                    initVal--;
                }else{
                    Thread.sleep(10);
                }
            }
        }

        this.destroy = true;
        System.out.println("The thread pool disposed.");
    }

    /**
     * 暴露线程大小给外部
     * @return
     */
    public int getQueueSize() {
        return queueSize;
    }

    /**
     * 暴露大小
     * @return
     */
    public int getSize() {
        return size;
    }


    public boolean destroy(){
        return this.destroy;
    }




    /**
     * 状态
     */
    private enum TaskState {
        FREE, RUNNING, BLICKED, DEAD
    }


    /**
     * 定义异常
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    /**
     * 提供接口
     */
    public interface DiscardPolicy {
        /**
         * @throws DiscardException
         */
        void discard() throws DiscardException;
    }


    private static class WorkerTask extends Thread {
        private volatile SimpleThreadPool2.TaskState taskState = SimpleThreadPool2.TaskState.FREE;


        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public SimpleThreadPool2.TaskState getTaskState() {
            return this.taskState;
        }


        /**
         * 线程停止后不能立刻消亡
         */
        @Override
        public void run() {
            OUTER:
            while (this.taskState != SimpleThreadPool2.TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = SimpleThreadPool2.TaskState.BLICKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }
                if (runnable != null) {
                    taskState = SimpleThreadPool2.TaskState.RUNNING;
                    runnable.run();
                    taskState = SimpleThreadPool2.TaskState.FREE;
                }


            }
        }


        /**
         * 关闭
         */
        public void close() {
            this.taskState = SimpleThreadPool2.TaskState.DEAD;
        }


    }


    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool2 threadPool = new SimpleThreadPool2();
        IntStream.rangeClosed(0, 40)
                .forEach(i ->
                        threadPool.submit(() -> {
                            System.out.println("The runnable " + i + "be serviced by " + Thread.currentThread() + "start");

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("The runnable " + i + "be serviced by " + Thread.currentThread() + "end");
                        })
                );
        Thread.sleep(10000);
        threadPool.shutdown();


    }


}
