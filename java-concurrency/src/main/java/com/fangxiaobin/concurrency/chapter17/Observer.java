package com.fangxiaobin.concurrency.chapter17;

/**
 * @author 方晓彬 2020/7/8 1:14
 **/
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    public abstract void update();





}
