package com.fangxiaobin.concurrency.chapter6;

/**
 * @author 方晓彬 2020/6/21 23:46
 * 优雅的停止线程
 * 3.守护线程方式
 **/
public class ThreadService {


    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task){
        executeThread = new Thread(){
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);

                runner.start();
                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                }
            }
        };
        executeThread.start();
    }


    /**
     *     显示调用结束方法
     */
    public void shutdown(long mills){
        long currentTime = System.currentTimeMillis();
        while (!finished){
            if((System.currentTimeMillis()-currentTime)>=mills){
                System.out.println("任务超时");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断");
                break;
            }
        }
        finished = false;
    }



}
