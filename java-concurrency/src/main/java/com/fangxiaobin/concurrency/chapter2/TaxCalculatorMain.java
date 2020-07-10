package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/20 0:06
 **/
public class TaxCalculatorMain {


    public static void main(String[] args) {
//        TaxCalaculator calaculator = new TaxCalaculator(10000d,20000d){
//
//            @Override
//            public double calcTax(){
//                return getSalary()*0.1+getBonus()*0.15;
//            }
//        };
//
//        double tax = calaculator.calcuate();

//        TaxCalaculator calaculator = new TaxCalaculator(10000d,2000d);
//        CalcuatorStrategy strategy = new SimpleCalculatorStrategy();
//        calaculator.setCalculatorStrategy(strategy);


//        TaxCalaculator calaculator = new TaxCalaculator(10000d, 2000d);
//        CalcuatorStrategy strategy = new SimpleCalculatorStrategy();
//        calaculator.setCalculatorStrategy((s, b) -> s * 0.1 + b * 0.15);





        TaxCalaculator calaculator = new TaxCalaculator(10000d, 2000d,(s, b) -> s * 0.1 + b * 0.15);
//        CalcuatorStrategy strategy = new SimpleCalculatorStrategy();
//        calaculator.setCalculatorStrategy((s, b) -> s * 0.1 + b * 0.15);





        System.out.println(calaculator.calcuate());

    }

}
