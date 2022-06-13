package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 18:00
*@description 三种结果：
 * 1、整正常结束，输出2000000
 * 2、抛出异常： 越界问题，扩容没有锁，导致内部一致性被破坏
 * 3、隐蔽错误，打印出大小不对
*/
import java.util.ArrayList;

public class ArrayListMultiThread {
    static ArrayList<Integer> al = new ArrayList<>(10);
    public static class AddThread implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                al.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(new AddThread());
        Thread t2 = new Thread(new AddThread());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(al.size());
    }
}
