package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 18:03
*@description LockSupport.park支持中断影响，不会抛出中断异常，会默默返回，可以重Thread.interrupted获取中断标志
*/
import java.util.concurrent.locks.LockSupport;

public class LockSupportIntDemo {
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
                if(Thread.interrupted()){
                    System.out.println(getName()+"被中断");
                }
            }
            System.out.println(getName()+"执行完毕");
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.interrupt();
        LockSupport.unpark(t2);
    }
}
