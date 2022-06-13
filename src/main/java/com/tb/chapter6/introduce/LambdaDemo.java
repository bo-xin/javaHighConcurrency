package com.tb.chapter6.introduce;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
*@author Tb
*@date 2022/6/6 15:21
*@description Lambda测试
*/
public class LambdaDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        numbers.forEach((Integer value)-> System.out.println(value));

        System.out.println("=====================");
        /**
         * jdk8自动把lambda中使用的变量视为final
         * */
        int num = 2;
        Function<Integer,Integer> stringConverter = (from) -> from * num;
        System.out.println(stringConverter.apply(3));
        /**
         * 使用num++导致编译错误
         * */
        //num++;
    }
}
