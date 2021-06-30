package com.lihd.java.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class SampleForCyclicBarrier3 {


    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始");
                    Thread.sleep(10000L);
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread().getName() + "完成");
                    countDownLatch.countDown();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
    }


}
