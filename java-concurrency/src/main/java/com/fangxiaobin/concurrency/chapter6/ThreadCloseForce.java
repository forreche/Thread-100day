package com.fangxiaobin.concurrency.chapter6;

/**
 * @author 方晓彬 2020/6/21 23:44
 * 优雅的停止线程
 * 3.守护线程方式
 **/
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();

        service.execute(() -> {
            //加载资源
//            while (true){
//
//            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.shutdown(10000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
