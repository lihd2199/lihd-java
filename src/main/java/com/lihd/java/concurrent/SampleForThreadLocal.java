package com.lihd.java.concurrent;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.Test;
import lombok.Data;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-06 15:16
 **/
public class SampleForThreadLocal {

    private ThreadLocal<People> threadLocal = new ThreadLocal<>();

    @Data
    @AllArgsConstructor
    @ToString
    private class People {

        private int id;

        private String name;

    }

    @Test
    public void test() throws InterruptedException {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        final CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {

            int I = i;
            new Thread(() -> {
                try {
                    threadLocal.set(new People(I, "name" + I));
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + threadLocal.get().toString());
                    threadLocal.remove();
                    System.out.println(Thread.currentThread().getName() + threadLocal.get());
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            }).start();

        }

        countDownLatch.await();

    }




    @Test
    public void testWithInitial(){

        final ThreadLocal<People> peopleThreadLocal = ThreadLocal.withInitial(() -> new People(1, "122"));

        System.out.println(peopleThreadLocal.get());

    }

}
