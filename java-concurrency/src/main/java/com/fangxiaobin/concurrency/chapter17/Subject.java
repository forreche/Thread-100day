package com.fangxiaobin.concurrency.chapter17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 方晓彬 2020/7/8 1:10
 *
 *
 * 观察者设计模式
 **/
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    private int state;

    public int getState(){
        return this.state;
    }

    public void setState(int state){
        if(state == this.state){
            return;
        }

        this.state = state;
        notifyAllObserver();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObserver(){
        observers.stream().forEach(Observer::update);
    }

}
