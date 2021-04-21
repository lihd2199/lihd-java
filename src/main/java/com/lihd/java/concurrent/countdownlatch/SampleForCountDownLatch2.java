package com.lihd.java.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class SampleForCountDownLatch2 {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 0; i < 12; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "释放");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }

        new CountDownLatch(1).await();

        System.out.println(Thread.currentThread().getName() + "完成");

    }


}
