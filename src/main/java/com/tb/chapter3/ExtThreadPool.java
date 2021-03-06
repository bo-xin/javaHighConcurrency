package com.tb.chapter3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
*@author Tb
*@date 2022/5/23 14:00
*@description ThreadPoolExecutor是一个可以扩展的线程池，提供了beforeExecute()
 * afterExecute()和terminated()三个接口对线程进行控制
*/
public class ExtThreadPool {
    public static class MyTask implements Runnable{
        public String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("正在执行:Thread ID:"+Thread.currentThread().getId()+",Task Name:"+name);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("准备执行"+((MyTask) r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("执行完成"+((MyTask) r).name);
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("TASK-GEYM-" + i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }
}
