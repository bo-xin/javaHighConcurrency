package com.tb.chapter4;
/**
*@author Tb
*@date 2022/5/24 11:16
*@description 由于线程不安全会导致报错：
 * java.lang.NumberFormatException: multiple points
 * java.lang.NumberFormatException: For input string: ""
*/
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo_01 {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static class ParseDate implements Runnable{
        int i = 0;

        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try{
                Date t = sdf.parse("2022-05-24 11:11:"+i % 60);
                System.out.println(i +":"+t);
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 1000; i++) {
            es.execute(new ParseDate(i));
        }
        es.shutdown();
    }
}
