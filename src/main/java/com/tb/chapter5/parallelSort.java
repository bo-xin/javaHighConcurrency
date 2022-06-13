package com.tb.chapter5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
*@author Tb
*@date 2022/6/1 10:38
*@description 并行排序
*/
public class parallelSort {
    static int[] arr;
    static ExecutorService pool = Executors.newCachedThreadPool();
    static int exchFlag = 1;
    static synchronized void setExchFlag(int v){
        exchFlag = v;
    }
    static synchronized int getExchFlag(){
        return exchFlag;
    }
    public static class OddEvenSortTask implements Runnable{
        int i;
        CountDownLatch latch;

        public OddEvenSortTask(int i, CountDownLatch latch) {
            this.i = i;
            this.latch = latch;
        }

        @Override
        public void run() {
            if(arr[i] > arr[i+1]){
                int temp = arr[i];
                arr[i] = arr[i+1];
                arr[i+1] = temp;
                setExchFlag(1);
            }
        }
    }

    public static void pOddEventSort(int[] arr) throws InterruptedException {
        int start = 0;
        while (getExchFlag() == 1 || start == 1){
            setExchFlag(0);
            CountDownLatch latch = new CountDownLatch(arr.length/2-(arr.length%2 == 0 ? start : 0));
            for (int i = start; i < arr.length-1; i+=2) {
                pool.submit(new OddEvenSortTask(i,latch));
            }
            latch.await();
            if(start == 0){
                start = 1;
            }else {
                start = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        arr = new int[]{1, 2, 341, 7, 8, 34, 65, 45};
        pOddEventSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
