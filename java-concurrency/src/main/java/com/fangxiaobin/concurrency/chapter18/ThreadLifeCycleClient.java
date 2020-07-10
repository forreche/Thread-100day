package com.fangxiaobin.concurrency.chapter18;

import java.util.Arrays;

/**
 * @author 方晓彬 2020/7/9 23:22
 **/
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1","2"));
    }


}
