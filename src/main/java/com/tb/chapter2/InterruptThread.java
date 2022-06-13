package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 17:18
*@description 中断方法：
 * 1、interrupt() 中断线程
 * 2、isInterrupted() 判断是否被中断
 * 3、interrupted()  判断是否被中断，并清除当前的中断状态
*/
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(){
            @Override
            public void run() {
                while (true){
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Interrupted!");
                        break;
                    }
                    try{
                        //sleep中被中断会抛出异常，处理时会清楚中断标志
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        System.out.println("Interrupted when sleep!");
                        Thread.currentThread().interrupt();
                    }
                    Thread.yield();
                }
            }
        };
        t1.start();
        Thread.sleep(2000);
        t1.interrupt();
    }
}
