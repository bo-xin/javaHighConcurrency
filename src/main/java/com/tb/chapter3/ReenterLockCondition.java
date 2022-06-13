package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 16:29
*@description 简介Condition的功能
 * 1、await():使得当前线程等待，同时释放锁。当其他线程使用signal或者signalAll是重新获取锁，或者当前线程被中断时，也能跳出等待
 * 2、awaitUninterruptibly():不会在等待过程中响应中断
 * 3、signal():唤醒一个等待的线程signalAll唤醒所有等待的线程
 *
 * 在执行signal或者await时必须先获取到相关锁
*/
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockCondition implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    @Override
    public void run() {
        try{
            lock.lock();
            condition.await();
            System.out.println("Thread is going on");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ReenterLockCondition tl = new ReenterLockCondition();
        Thread t1 = new Thread(tl);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        System.out.println("main Thread");
        condition.signal();
        lock.unlock();
    }
}
