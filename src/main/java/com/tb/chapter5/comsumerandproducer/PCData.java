package com.tb.chapter5.comsumerandproducer;
/**
*@author Tb
*@date 2022/5/25 14:37
*@description 队列中的数据
*/
public class PCData {
    private final int intData;

    public PCData(int intData) {
        this.intData = intData;
    }

    public PCData(String d){
        this.intData = Integer.valueOf(d);
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
