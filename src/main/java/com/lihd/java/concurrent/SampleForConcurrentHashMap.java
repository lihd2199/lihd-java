package com.lihd.java.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: lihd-java
 * @description: Sample For CurrentHashMap
 * @author: li_hd
 * @create: 2020-01-10 09:53
 **/
public class SampleForConcurrentHashMap {


    public static void main(String[] args) throws InterruptedException {

        SampleForConcurrentHashMap sampleForConcurrentHashMap = new SampleForConcurrentHashMap();
        sampleForConcurrentHashMap.test1();
        sampleForConcurrentHashMap.test2();
        sampleForConcurrentHashMap.test3();

    }


    private void test1() throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        map.put("key", 1);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                int key = map.get("key") + 1;   //step 1
                map.put("key", key);   //step 2
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("test1  "+map.get("key"));
        executorService.shutdown();
    }


    private void test2() throws InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        map.put("key", new AtomicInteger(1));
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                map.get("key").getAndIncrement();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("test2  " + map.get("key").get());
        executorService.shutdown();
    }

    private void test3() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        AtomicInteger a = new AtomicInteger(1);
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                a.incrementAndGet();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("test3  " + a.get());
        executorService.shutdown();
    }


}
