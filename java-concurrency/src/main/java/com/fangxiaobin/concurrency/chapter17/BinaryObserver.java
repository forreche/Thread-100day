package com.fangxiaobin.concurrency.chapter17;

/**
 * @author 方晓彬 2020/7/8 1:17
 **/
public class BinaryObserver extends Observer{

    public BinaryObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary string : "+Integer.toBinaryString(subject.getState()));
    }


}
