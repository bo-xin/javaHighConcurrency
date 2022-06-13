package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 17:48
*@description 线程阻塞LockSupport
 * 通过类似信号量的原理实现;每一个线程分配一个许可。如果许可可用park立即返回，并且消费这个许可
 * 如果许可不可用就会阻塞，而unpark使得一个许可变得可用
*/
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in"+ getName());
                LockSupport.park(this);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
