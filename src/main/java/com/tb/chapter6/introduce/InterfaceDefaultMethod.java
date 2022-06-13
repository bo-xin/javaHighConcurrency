package com.tb.chapter6.introduce;
/**
*@author Tb
*@date 2022/6/6 15:04
*@description 接口默认方法
*/
public class InterfaceDefaultMethod {
    public interface IHorse{
        void eat();
        default void run(){
            System.out.println("hourse run");
        }
    }

    interface IAnimal{
        default void breath(){
            System.out.println("breath");
        }
    }

    interface IDonkey{
        void eat();
        default void run(){
            System.out.println("Donkey run");
        }
    }

    class Mule implements IHorse,IDonkey,IAnimal{
        @Override
        public void eat() {
            System.out.println("mule eat");
        }
        /**
         * 解决多继承中的重名问题
         * 设置指定的接口类
         * */
        @Override
        public void run() {
            IHorse.super.run();
        }

    }
}
