package com.lihd.java;


import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class App {

    @Test
    public void zuse() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Thread thread = new Thread(() -> {
            try {
                countDownLatch.countDown();
                System.out.println("开始阻塞");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
                e.printStackTrace();
            }
        });

        thread.start();
        countDownLatch.await();
        thread.interrupt();

        new CountDownLatch(1).await();

    }

    @Test
    public void notzuse() throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);

        final Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("正常执行");
                countDownLatch.countDown();
            }
            System.out.println("跳出循环");
        });

        thread.start();
        countDownLatch.await();
        thread.interrupt();

        new CountDownLatch(1).await();

    }





}
