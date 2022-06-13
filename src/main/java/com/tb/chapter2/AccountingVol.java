package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 17:39
*@description 两个线程对i累加，导致数据不一致，即时使用volatile
*/
public class AccountingVol implements Runnable{
    static AccountingVol instance = new AccountingVol();
    static volatile int i = 0;
    public static void increase(){
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t2.join();t2.join();
        System.out.println(i);
    }
}
