package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 16:15
*@description 公平锁，实现获得锁的先到先得
*/
import java.util.concurrent.locks.ReentrantLock;

public class FairLock implements Runnable{
    public static ReentrantLock fairLock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true){
            try{
                fairLock.lock();
                System.out.println(Thread.currentThread().getName()+":获得锁");
            }finally {
                fairLock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        FairLock rl = new FairLock();
        Thread t1 = new Thread(rl, "Thread_t1");
        Thread t2 = new Thread(rl,"Thread_t2");
        t1.start();t2.start();
    }
}
