package com.tb.chapter6.introduce;

import java.util.Arrays;

/**
*@author Tb
*@date 2022/6/1 16:12
*@description
 * 函数式编程的特点：
 * 1、函数作为一等公民
 *     a.可以将函数作为参数传给另一个函数
 *     b.函数可以作为另一个函数的返回值
 * 2、无副作用
 *     副作用:指函数在调用过程中除了返回值外，还修改了函数外部的状态
 *     显式函数:与外界交互数据的唯一渠道就是参数和返回值
 *     隐式函数:会读取外部信息，或者可能修改外部信息
 * 3、申明式的(Declarative)
 *     相对于命令式的(Imperative)
 *
 * 4、不变对象
 *
 *5、易于并行
 *
 * 6、更少的代码
 */
public class Introduction {
    public static void main(String[] args) {
        int[] iArr = {1,2,3,4,5,6,9,8,7,4,2};
        Arrays.stream(iArr).map((x)->x=x+1).forEach(System.out::println);
        System.out.println();
        Arrays.stream(iArr).forEach(System.out::println);
    }
}
