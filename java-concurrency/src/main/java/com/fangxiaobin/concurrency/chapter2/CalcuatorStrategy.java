package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/20 0:11
 **/

@FunctionalInterface
public interface CalcuatorStrategy {

     double calculate(double salary,double bonus);


}
