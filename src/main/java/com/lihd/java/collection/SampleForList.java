package com.lihd.java.collection;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: lihd-java
 * @description:
 * @author: 黎
 * @create: 2020-05-08 19:48
 **/
public class SampleForList {

    @Test
    public void test() {

        List<String> integers = new ArrayList<>();

        integers.add("1");
        integers.add("2");
        integers.add("3");
        integers.add("4");

//        for (int i = 0; i < integers.size(); i++) {
//            if (integers.get(i) == 5) {
//                integers.remove(i);
//                integers.remove(integers.get(i));
//            }
//        }

//
//        for (String integer : integers) {
//            if("4".equals(integer)){
//                integers.remove("4");
//            }
//        }


        Iterator<String> var5 = integers.iterator();

        while (var5.hasNext()) {
            String integer = var5.next();
            if ("2".equals(integer)) {
                var5.remove();
            }
        }

//
//        for (Integer integer : integers) {
//            if (integer == 8) {
//                integers.remove(integer);
//                integers.add(1233);
//            }
//        }


    }


    volatile CopyOnWriteArrayList<Double> integers = new CopyOnWriteArrayList<>();

    @Test
    public void test_copyOnWrite() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);


        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    for (int i1 = 0; i1 < 2000; i1++) {
                        integers.add(Math.random());
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        }

        countDownLatch.await();
        System.out.println(integers.size());


    }


}
