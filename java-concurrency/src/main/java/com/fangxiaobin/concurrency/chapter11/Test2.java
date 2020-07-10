package com.fangxiaobin.concurrency.chapter11;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author 方晓彬 2020/6/30 22:37
 **/
public class Test2 {

    public void test() {
        //Thread.currentThread.getStackTrace()  获取堆栈信息
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e -> !e.isNativeMethod())
                //输出类名 方法名 行数
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber())
                .ifPresent(System.out::println)
                );

    }

}
