package com.tb.chapter2;

import lombok.Data;

/***
 * 在ChangObjectThread中增加stopMe()方法
 */
public class StopThreadSafe {
    public static StopThreadUnsafe.User u = new StopThreadUnsafe.User();
    @Data
    public static class User{
        private int id;
        private String name;

        public User() {
            id = 0;
            name = "0";
        }
    }
    public static class ChangeObjectThread extends Thread{
        volatile boolean stopme = false;
        public void stopMe(){
            stopme = true;
        }
        @Override
        public void run() {
            while (true){
                if(stopme){
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u){
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                }
                Thread.yield();
            }
        }
    }
    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true){
                synchronized (u){
                    if(u.getId() != Integer.parseInt(u.getName())){
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        new ReadObjectThread().start();
        while (true){
            ChangeObjectThread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stopMe();
        }
    }
}
