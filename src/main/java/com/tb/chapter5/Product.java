package com.tb.chapter5;
/**
*@author Tb
*@date 2022/5/25 14:26
*@description 不变模式例子
*/
public final class Product {    //确保没有子类
    private final String no;    //私有属性，不会被其他对象获取
    private final String name;  //final保证不会被两次复制
    private final double price;

    public Product(String no, String name, double price) {
        super();
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
