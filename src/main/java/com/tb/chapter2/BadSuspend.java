package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 17:14
*@description 不好的线程挂起方法，suspend 挂起 resume 继续执行
 * suspend挂起不会释放资源，等到resume后继续执行
 * 当resume先于suspend时会导致线程永久挂起，并占用锁
*/
public class BadSuspend {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("t1");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("in "+getName());
                /*try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }*/
                Thread.currentThread().suspend();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        t1.start();
        Thread.sleep(100);
        t2.start();
        t1.resume();
        t2.resume();
        t1.join();
        t2.join();
    }
}
