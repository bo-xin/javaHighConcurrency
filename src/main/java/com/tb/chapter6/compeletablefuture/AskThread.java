package com.tb.chapter6.compeletablefuture;
/**
*@author Tb
*@date 2022/6/6 16:21
*@description 完成后通知线程
*/
import java.util.concurrent.CompletableFuture;

public class AskThread implements Runnable{
    CompletableFuture<Integer> re = null;

    public AskThread(CompletableFuture<Integer> re) {
        this.re = re;
    }


    @Override
    public void run() {
        int myRe = 0;
        try{
            //get方法会阻塞，等待future给它数据
            myRe = re.get()*re.get();
        }catch (Exception e){

        }
        System.out.println(myRe);
    }

    public static void main(String[] args) throws InterruptedException {
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        new Thread(new AskThread(future)).start();
        Thread.sleep(1000);
        //给予future数据
        future.complete(60);
    }
}
