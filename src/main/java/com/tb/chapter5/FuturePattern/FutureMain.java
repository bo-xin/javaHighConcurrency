package com.tb.chapter5.FuturePattern;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构造FutureTask
        FutureTask<String> future = new FutureTask<>(new RealData1("a"));
        ExecutorService service = Executors.newFixedThreadPool(1);
        //执行FutureTask
        service.submit(future);
        System.out.println("请求完毕");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }
        System.out.println("数据="+future.get());
    }
}
