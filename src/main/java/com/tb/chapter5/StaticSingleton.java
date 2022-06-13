package com.tb.chapter5;
/**
*@author Tb
*@date 2022/5/25 14:20
*@description 使用静态内部类，在调用的时候进行实例化
*/
public class StaticSingleton {
    private StaticSingleton(){
        System.out.println("staticSingleton is create");
    }
    private static class SingletonHolder{
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance(){
        return SingletonHolder.instance;
    }
}
