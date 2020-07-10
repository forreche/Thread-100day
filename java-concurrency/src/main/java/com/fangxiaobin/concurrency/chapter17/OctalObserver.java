package com.fangxiaobin.concurrency.chapter17;

/**
 * @author 方晓彬 2020/7/8 1:19
 **/
public class OctalObserver extends Observer {

    public OctalObserver(Subject subject){
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("OctalObserver string : "+Integer.toBinaryString(subject.getState()));
    }

}
