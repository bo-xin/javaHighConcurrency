package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/23 13:58
*@description 记录线程池的创建，另一方面将所有的线程设置为守护线程
*/
import java.util.concurrent.*;

public class customizeThreadFactory {
    public static void main(String[] args) throws InterruptedException{
        RejectTHreadPoolDemo.MyTask task = new RejectTHreadPoolDemo.MyTask();
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setDaemon(true);
                        System.out.println("creat "+t);
                        return t;
                    }
                });
        for (int i = 0; i < 5; i++) {
            es.submit(task);
        }
        Thread.sleep(2000);
    }
}
