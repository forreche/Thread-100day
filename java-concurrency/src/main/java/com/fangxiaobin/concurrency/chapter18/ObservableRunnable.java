package com.fangxiaobin.concurrency.chapter18;

import sun.plugin.viewer.LifeCycleManager;

/**
 * @author 方晓彬 2020/7/9 22:32
 *
 * 使用观察者设计模式观察线程生命周期
 **/
public abstract class ObservableRunnable implements Runnable{

    final protected LifeCycleListener listener;

    public ObservableRunnable(final LifeCycleListener listener){
        this.listener = listener;
    }

    protected void notifyChange(final RunnableEvent event){
        listener.onEvent(event);
    }

    public enum RunnableState{
        RUNNING,ERROR,DONE;
    }

    public static class RunnableEvent{
        private final RunnableState state;
        private final Thread thread;
        private final Throwable cause;

        public RunnableEvent(RunnableState state,Thread thread,Throwable cause){
            this.state = state;
            this.thread = thread;
            this.cause = cause;
        }

        public RunnableState getState() {
            return state;
        }

        public Thread getThread() {
            return thread;
        }

        public Throwable getCause() {
            return cause;
        }
    }

}
