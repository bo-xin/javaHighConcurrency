package com.tb.chapter5;
/**
*@author Tb
*@date 2022/5/25 14:20
*@description 在调用时执行实例化，使用同步加锁，锁竞争激烈的场合对性能影响
*/
public class LazySingleton {
    private LazySingleton(){
        System.out.println("LazySingleton is create");
    }
    private static LazySingleton instance = null;
    public static synchronized LazySingleton getInstance(){
        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}
