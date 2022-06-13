package com.tb.chapter5.consumerandproducerdisruptor;
/**
*@author Tb
*@date 2022/5/25 15:27
*@description 生产者
*/
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class Producer {
    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer<PCData> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }
    //读取ByteBuffer中的数据，并装载到环形缓冲区
    public void pushData(ByteBuffer bb){
        long sequence = ringBuffer.next();
        try{
            PCData event = ringBuffer.get(sequence);
            event.setValue(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }
}
