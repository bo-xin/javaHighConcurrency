package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 16:58
*@description volatile不能保证某些符合操作的原子性
*/
public class MultiThreadAddInt {
    static volatile int i = 0;
    public static class PlusTask implements Runnable{
        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new PlusTask());
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }
        System.out.println(i);
    }
}
