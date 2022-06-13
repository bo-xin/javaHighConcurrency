package com.tb.chapter5.consumerandproducerdisruptor;
/**
*@author Tb
*@date 2022/5/25 15:20
*@description 消费者实现WorkHandler接口
*/
import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
    @Override
    public void onEvent(PCData event) throws Exception {
        System.out.println(Thread.currentThread().getId()+":Event:--"
            +event.getValue()*event.getValue()+"--");
    }
}
