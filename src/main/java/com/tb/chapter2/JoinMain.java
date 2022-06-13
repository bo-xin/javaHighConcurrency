package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 17:20
*@description 等待线程结束join，谦让yeild
 * 1、join()无线等待，会一直阻塞当前线程，直到目标线程执行完毕
 * 2、join(long millis) 限期等待
*/
public class JoinMain {
    public volatile static int i = 0;
    public static class AddThread extends Thread{
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++);
        }
    }

    public static void main(String[] args) throws InterruptedException{
        AddThread at = new AddThread();
        at.start();
        at.join();
        System.out.println(i);
    }
}
