package com.tb.chapter2;

import java.util.HashMap;
import java.util.Map;
/**
*@author Tb
*@date 2022/5/18 18:06
*@description 三种情况：
 * 1、程序正常结束，打印符合预期
 * 2、程序正常结束，打印不符合预期
 * 3、程序永远无法结束
*/
public class HashmapMultiThread {
    static Map<String,String> map = new HashMap<>();
    public static class AddThread implements Runnable{
        int start = 0;

        public AddThread(int start) {
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < 100000; i+=2) {
                map.put(Integer.toString(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new HashmapMultiThread.AddThread(0));
        Thread t2 = new Thread(new HashmapMultiThread.AddThread(1));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(map.size());
    }
}
