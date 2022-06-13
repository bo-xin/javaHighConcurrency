package com.tb.chapter5;
/**
 *@author Tb
 *@date 2022/5/30 11:43
 *@description 串行实现奇偶排序
 */
public class serialSort {



    public static void oddEvenSort(int[] arr){
        int exchangeFlag = 1,start = 0;
        while (exchangeFlag == 1 || start == 1){
             exchangeFlag = 0;
            for (int i = start; i < arr.length-1; i+=2) {
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    exchangeFlag = 1;
                }
            }
            if(start == 0){
                start = 1;
            }else {
                start = 0;
            }
        }
    }
}
