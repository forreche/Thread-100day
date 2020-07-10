package com.fangxiaobin.concurrency.chapter1;

/**
 * @author 方晓彬 2020/6/18 23:20
 *
 * new方式创建线程
 * 内部类方式创建线程
 * 匿名内部类创建线程
 **/
public class TryConcurrency {

    private static void readFromDateBase(){

        try {
            println("开始读数据库");
            Thread.sleep(10_000);
            println("读取数据完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("数据处理完并且是成功的");
    }


    private static void writeDataToFile(){
        try {
            println("开始写文件");
            Thread.sleep(10_00);
            println("写数据完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        println("数据处理完并且是成功的");
    }




    private static void println(String message){
        System.out.println(message);
    }


    public static void main(String[] args) {

        //匿名局部内部类
        new Thread("read"){
            @Override
            public void run() {
                readFromDateBase();
            }
        }.start();


        new Thread("writ"){
            @Override
            public void run() {
                writeDataToFile();
            }
        }.start();


        //内部类

       /* Thread t1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    println("task 1=>"+i);
                }

            }
        };

        t1.start();

        for (int i = 0; i < 10000; i++) {
            println("task 2=>"+i);
        }
*/
        /*

        for (int j = 0; j < 100; j++) {
            println("task 1=>"+j);
        }*/

//        readFromDateBase();
//        writeDataToFile();


//        try {
//            Thread.sleep(10_000*3L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }





}
