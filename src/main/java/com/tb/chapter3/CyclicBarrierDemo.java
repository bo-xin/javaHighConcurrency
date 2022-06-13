package com.tb.chapter3;
/**
*@author Tb
*@date 2022/5/19 17:31
*@description 使用循环栅栏实现多个线程相互等待
 * 两种异常：
 * InterruptedException：中断异常；使得线程可以响应外部紧急事件
 * BrokenBarrierException：CyclicBarrier特有，表示当前CyclicBarrier被破坏，直接打道回府
 *
*/
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
*@author Tb
*@date 2022/5/19 17:08
*@description 循环栅栏，
*/
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(String soldier, CyclicBarrier cyclicBarrier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try{
                //等待士兵到齐
                cyclicBarrier.await();
                doWork();
                //等待士兵任务完成
                cyclicBarrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }
        }
        void doWork(){
            try{
                Thread.sleep(Math.abs(new Random().nextInt()/10000));
                //Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(soldier + "：任务完成");
        }
    }
    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令：[士兵"+N+"个，任务完成]");
            }else {
                System.out.println("司令：[士兵"+N+"个，集合完毕]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        final int N = 10;
        Thread[] allSoldier = new Thread[10];
        boolean flag = false;
        CyclicBarrier cyclic = new CyclicBarrier(N,new BarrierRun(flag,N));
        System.out.println("集合队伍!");
        for (int i = 0; i < 10; i++) {
            System.out.println("士兵"+i+"报道！");
            allSoldier[i] = new Thread(new Soldier("士兵"+i,cyclic));
            allSoldier[i].start();
            /*if(i == 5){
                allSoldier[5].interrupt();
            }*/
        }
    }
}
