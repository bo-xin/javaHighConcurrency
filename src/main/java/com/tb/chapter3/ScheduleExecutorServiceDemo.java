package com.tb.chapter3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
*@author Tb
*@date 2022/5/20 15:29
*@description 计划任务
 * ses.scheduleWithFixedDelay():周期性调度，在任务结束后开始下一个周期
 * ses.scheduleAtFixedRate():周期性调度，任务时间算在周期之内
 * ses.schedule():在给定时间调度一次
*/
public class ScheduleExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
        ses.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                    System.out.println(System.currentTimeMillis()/1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        },0,2, TimeUnit.SECONDS);

    }
}
