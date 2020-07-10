package com.fangxiaobin.concurrency.chapter19;

/**
 * @author 方晓彬 2020/7/10 1:04
 * SingLe 设计模式
 * SharedResource 共享资源
 **/
public class Gate {


    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";


    /**
     * 临界值
     * @param name
     * @param address
     *
     * 对这里资源操作时 会发生争抢
     *
     * 这里加锁时 会慢
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }





    private void verify() {

        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*************BROKEN*************" + toString());
        }
    }

    /**
     * 这种情况会出现数据错误情况
     * @return
     */
   /* @Override
    public  String toString() {
        return "No. " + counter + ":" + name + "," + address;
    }

     public synchronized void pass(String name, String address) {
        this.counter++;
        this.name = name;
        this.address = address;
        verify();
    }
    */


    @Override
    public synchronized String toString() {
        return "No. " + counter + ":" + name + "," + address;
    }

}
