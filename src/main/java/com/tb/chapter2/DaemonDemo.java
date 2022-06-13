package com.tb.chapter2;
/**
*@author Tb
*@date 2022/5/18 17:13
*@description 守护线程的使用
 * 注：先启动后设置守护线程会导致设置失败，当做用户进程运行
 * 会抛出异常
 * Exception in thread "main" java.lang.IllegalThreadStateException
 * 	at java.base/java.lang.Thread.setDaemon(Thread.java:1403)
 * 	at com.tb.chapter2.DaemonDemo.main(DaemonDemo.java:25)
*/
public class DaemonDemo {
    public static class DaemonT extends Thread{
        @Override
        public void run() {
            while (true){
                System.out.println("I am alive!");
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        DaemonT t = new DaemonT();
        t.setDaemon(true);
        t.start();
        Thread.sleep(2000);
    }
}
