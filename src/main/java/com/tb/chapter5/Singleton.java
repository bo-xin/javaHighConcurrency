package com.tb.chapter5;
/**
*@author Tb
*@date 2022/5/25 14:20
*@description 直接进行实例化，会在最开始进行实例化，没有锁
*/
public class Singleton {
    private Singleton(){
        System.out.println("Singleton is create");
    }
    private static Singleton instance = new Singleton();
    public static Singleton getInstance(){
        return instance;
    }
}
