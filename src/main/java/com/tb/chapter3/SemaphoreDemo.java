package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 16:59
*@description 通过信号量控制线程
*/
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo implements Runnable{
    final Semaphore semp = new Semaphore(5);
    @Override
    public void run() {
        try{
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+"：done！");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
