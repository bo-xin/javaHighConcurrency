package com.tb.chapter3;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class JMHMap_Test {
    static Map hashMap = new HashMap();
    static Map syncHashMap = Collections.synchronizedMap(new HashMap<>());
    static Map concurrentHahMap = new ConcurrentHashMap();

    @Setup
    public void setup(){
        for (int i = 0; i < 10000; i++) {
            hashMap.put(Integer.toString(i),Integer.toString(i));
            syncHashMap.put(Integer.toString(i),Integer.toString(i));
            concurrentHahMap.put(Integer.toString(i),Integer.toString(i));
        }
    }

    @Benchmark
    public void hashMapGet(){
        hashMap.get("4");
    }

    @Benchmark
    public void syncHahMapGet(){
        syncHashMap.get("4");
    }

    @Benchmark
    public void concurrentHashMapGet(){
        concurrentHahMap.get("4");
    }
    @Benchmark
    public void hashMapSize(){
        hashMap.size();
    }

    @Benchmark
    public void syncHahMapSize(){
        syncHashMap.size();
    }

    @Benchmark
    public void concurrentHashMapSize(){
        concurrentHahMap.size();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(JMHMap_Test.class.getSimpleName()).forks(1).build();
        new Runner(opt).run();
    }
}
