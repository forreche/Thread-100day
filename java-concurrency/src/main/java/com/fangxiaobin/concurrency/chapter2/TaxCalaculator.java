package com.fangxiaobin.concurrency.chapter2;

/**
 * @author 方晓彬 2020/6/20 0:04
 **/
public class TaxCalaculator {

    private final double salary;

    private final double bonus;

//    private CalcuatorStrategy calcuatorStrategy;

    private final CalcuatorStrategy calcuatorStrategy;


//    public TaxCalaculator(double salary,double bonus ){
//        this.bonus = bonus;
//        this.salary = salary;
//    }
    //    public void setCalculatorStrategy(CalcuatorStrategy calculatorStrategy){
//        this.calcuatorStrategy = calculatorStrategy;
//    }


    public TaxCalaculator(double salary, double bonus, CalcuatorStrategy calcuatorStrategy) {
        this.bonus = bonus;
        this.salary = salary;
        this.calcuatorStrategy = calcuatorStrategy;
    }


    protected double calcTax() {
        return 0.0d;
    }


    public double calcuate() {
        return calcuatorStrategy.calculate(salary, bonus);
    }


    public double getBonus() {
        return bonus;
    }

    public double getSalary() {
        return salary;
    }


}
