package com.tb.chapter6.parallelstream;

import java.util.stream.IntStream;
/**
*@author Tb
*@date 2022/6/6 16:09
*@description 使用 并行流进行数据过滤
*/
public class PrimeUtil {
    public static boolean isPrime(int number){
        int tmp = number;
        if(tmp < 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(tmp); i++) {
            if(tmp % i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long count = IntStream.range(1, 1000000).filter(PrimeUtil::isPrime).count();
        System.out.println(count);
        long count1 = IntStream.range(1, 1000000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println(count1);
    }
}
