package com.fangxiaobin.concurrency.chapter18;

/**
 * @author 方晓彬 2020/7/9 22:50
 **/
public interface LifeCycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);


}
