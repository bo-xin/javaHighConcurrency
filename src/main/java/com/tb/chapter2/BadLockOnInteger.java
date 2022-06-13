package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 18:20
*@description Integer属于不变对象，Integer修改会新建一个对象
*/
public class BadLockOnInteger implements Runnable{
    public static Integer i = 10;
    static BadLockOnInteger instance = new BadLockOnInteger();

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            synchronized (i){
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();t2.start();
        t1.join();t2.join();
        System.out.println(i);
    }
}
