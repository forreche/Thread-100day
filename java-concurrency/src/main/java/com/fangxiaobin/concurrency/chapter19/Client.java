package com.fangxiaobin.concurrency.chapter19;

/**
 * @author 方晓彬 2020/7/10 1:13
 *
 * Single
 **/
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();

        User bj = new User("BeiJing","BaoBao",gate);
        User sh = new User("ShangHai","SHang",gate);
        User gz = new User("GuangZhou","GG",gate);


        bj.start();
        sh.start();
        gz.start();
    }

}
