package com.lihd.java.collection;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-06-11 10:54
 **/
public class SampleForHashMap {

    /**
     * 校验HashMap 线程不安全
     */
    public Integer threadSafe(int size) throws InterruptedException {

        Map<Integer, Integer> map = new HashMap<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(size);
        CountDownLatch countDownLatch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            int I = i;
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    map.put(I, I);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        return map.size();
    }









}