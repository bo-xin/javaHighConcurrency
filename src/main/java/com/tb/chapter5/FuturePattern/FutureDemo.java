package com.tb.chapter5.FuturePattern;

import com.google.common.util.concurrent.*;

import java.util.concurrent.Executors;

/**
*@author Tb
*@date 2022/5/25 16:14
*@description 使用Guava改写，调用完成后自动通知主线程
 *
*/
public class FutureDemo {
    public static void main(String[] args) throws InterruptedException {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> task = service.submit(new RealData1("x"));
       /* task.addListener(()->{
            System.out.println("异步处理成功");
            try{
                System.out.println(task.get());
            }catch (Exception e){
                e.printStackTrace();
            }
        },MoreExecutors.directExecutor());*/
        //增加对异常的处理
        Futures.addCallback(task, new FutureCallback<String>() {
            @Override
            public void onSuccess(String s) {
                System.out.println("异步处理成功,result="+s);
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("异步处理失败,e="+throwable);
            }
        },MoreExecutors.directExecutor());

        System.out.println("main task done......");
        Thread.sleep(3000);
        service.shutdown();
    }
}
