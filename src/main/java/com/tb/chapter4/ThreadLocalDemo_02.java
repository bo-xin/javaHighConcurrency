package com.tb.chapter4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author Tb
*@date 2022/5/24 11:16
*@description 使用ThreadLocal进行存储
*/
public class ThreadLocalDemo_02 {
    static ThreadLocal<SimpleDateFormat> tl = new ThreadLocal<>();
    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try{
                if(tl.get() == null){
                    tl.set(new SimpleDateFormat("yyyy-MM-dd hh:MM:ss"));
                }
                Date t = tl.get().parse("2022-05-24 11:11:"+i % 60);
                System.out.println(i +":"+t);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ThreadLocalDemo_02.ParseDate(i));
        }
        es.shutdown();
    }
}
