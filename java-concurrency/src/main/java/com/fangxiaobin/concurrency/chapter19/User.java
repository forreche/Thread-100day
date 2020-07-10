package com.fangxiaobin.concurrency.chapter19;

/**
 * @author 方晓彬 2020/7/10 1:10
 **/
public class User extends Thread{

    private String myName;

    private String myyAddress;

    private final Gate gate;

    public User(String name,String myyAddress,Gate gate){
        this.myName = name;
        this.myyAddress = myyAddress;
        this.gate = gate;
    }


    @Override
    public void run() {
        System.out.println(myName+"BEGIN");
        while (true){
            this.gate.pass(myName,myyAddress);
        }
    }


}
