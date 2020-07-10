package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/20 0:15
 **/
public class SimpleCalculatorStrategy implements CalcuatorStrategy {


    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
