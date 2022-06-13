package com.tb.chapter5.FuturePattern;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("name");
        System.out.println("请求完毕");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){

        }
        System.out.println("数据="+data.getResult());
    }
}
