package com.tb.chapter6.methodRef;

import java.util.ArrayList;
import java.util.List;

public class BadMethodRef {
    public static void main(String[] args) {
        List<Double> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(Double.valueOf(i));
        }
        /**
         * 由于double中包含两个toString函数无法知道使用哪一个方法，报错
         * */
        //numbers.stream().map(Double::toString).forEach(System.out::println);
    }
}
