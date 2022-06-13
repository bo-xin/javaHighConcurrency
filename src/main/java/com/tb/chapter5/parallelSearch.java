package com.tb.chapter5;
/**
*@author Tb
*@date 2022/5/30 11:44
*@description 并行搜索算法
 * 注意参数传递的情况
*/
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class parallelSearch {
    static int[] arr = new int[]{1, 5, 9, 8, 4, 6, 45, 7, 66, 32, 54, 451};
    static ExecutorService pool = Executors.newCachedThreadPool();
    static final int Thread_num = 2;
    static AtomicInteger result = new AtomicInteger(-1);

    public static int search(int searchValue,int beginPos,int endPos){
        int i = 0;
        for(i = beginPos;i < endPos;i++){
            if(result.get() >= 0){
                return result.get();
            }
            if(arr[i] == searchValue){
                if(!result.compareAndSet(-1,i)){
                    return result.get();
                }
                return i;
            }
        }
        return -1;
    }

    public static class SearchTask implements Callable<Integer>{
        int begin, end, searchValue;

        public SearchTask(int begin, int end, int searchValue) {
            this.begin = begin;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            int re = search(searchValue,begin,end);
            return re;
        }
    }

    public static int pSearch(int searchValue) throws ExecutionException, InterruptedException {
        int subArrSize = arr.length/Thread_num+1;
        List<Future<Integer>> re = new ArrayList<>();
        for (int i = 0; i < arr.length; i+=subArrSize) {
            int end = i + subArrSize;
            if(end >= arr.length) end = arr.length;
            re.add(pool.submit(new SearchTask(i,end,searchValue)));
        }
        pool.shutdown();
        for (Future<Integer> fu : re) {
            if(fu.get() >= 0) return fu.get();
        }
        return -1;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(pSearch(45));
    }
}
