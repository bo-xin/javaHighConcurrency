package com.tb.chapter4;
/**
*@author Tb
*@date 2022/5/25 12:36
*@description 贵宾卡问题，会导致ABA问题出现
 * 对小于20元的贵宾卡充值20
*/
import java.util.concurrent.atomic.AtomicReference;

public class CakeStoreDemo {
    static AtomicReference<Integer> money = new AtomicReference<Integer>();

    public static void main(String[] args) {
        money.set(19);
        for (int i = 0; i < 3; i++) {
            new Thread(){
                @Override
                public void run() {
                    while (true){
                        while (true){
                            Integer m = money.get();
                            if(m < 20){
                                if(money.compareAndSet(m,m+20)){
                                    System.out.println("余额小于20，充值成功，余额："+money.get()+"元");
                                    break;
                                }
                            }else {
                                System.out.println("余额大于20，不需要充值");
                                break;
                            }
                        }
                    }
                }
            }.start();
        }
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true){
                        Integer m = money.get();
                        if(m > 10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m,m-10)){
                                System.out.println("成功消费10元，余额："+money.get());
                                break;
                            }
                        }else {
                            System.out.println("没有足够金额");
                            break;
                        }
                    }
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

}
