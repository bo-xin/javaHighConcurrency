package com.tb.chapter5.FuturePattern;
/**
*@author Tb
*@date 2022/5/25 16:13
*@description JDK自带的Future。get会阻塞
*/
import java.util.concurrent.Callable;

public class RealData1 implements Callable<String> {
    private String para;
    public RealData1(String para){
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }
        return sb.toString();
    }
}
