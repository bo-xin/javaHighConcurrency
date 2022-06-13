package com.tb.chapter5.consumerandproducerdisruptor;
/**
*@author Tb
*@date 2022/5/25 15:22
*@description 在初始化时候构造所有的缓冲区对象实例
*/
import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory<PCData> {
    @Override
    public PCData newInstance() {
        return new PCData();
    }
}
