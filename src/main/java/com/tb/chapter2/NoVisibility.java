package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 16:58
*@description volatile可以保证数据的可见性和有序性
*/
public class NoVisibility {
    private volatile static  boolean ready;
    private volatile static  int number;
    public static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready){
                System.out.println(number);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new ReadThread().start();
        Thread.sleep(1000);
        number = 42;
        ready = true;
        Thread.sleep(10000);
    }
}
