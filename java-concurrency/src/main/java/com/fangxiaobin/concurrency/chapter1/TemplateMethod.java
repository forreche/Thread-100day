package com.fangxiaobin.concurrency.chapter1;

/**
 * @author 方晓彬 2020/6/19 2:08
 * 模版方法
 **/
public  class TemplateMethod {

    public final void print(String message){
        System.out.println("######################");

        wrapPrint(message);

        System.out.println("######################");
    }

    protected  void wrapPrint(String message){

    }

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod(){

            @Override
            protected void wrapPrint(String message) {
                System.out.println("*"+message+"*");
            }
        };
        t1.print("hello Thread");
    }



}
