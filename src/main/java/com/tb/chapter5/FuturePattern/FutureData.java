package com.tb.chapter5.FuturePattern;
/**
*@author Tb
*@date 2022/5/25 16:00
*@description 真实数据的代理
*/
public class FutureData implements Data{
    protected RealData realData = null;
    protected boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        if(isReady){
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady){
            try{
                wait();
            }catch (InterruptedException e){

            }
        }
        return realData.getResult();
    }
}
