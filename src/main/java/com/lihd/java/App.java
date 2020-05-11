package com.lihd.java;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class App {


    public static void main(String[] args) throws InterruptedException {


        final AtomicInteger atomicInteger = new AtomicInteger(0);

        final CountDownLatch countDownLatch = new CountDownLatch(3);

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 3000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        threadPoolExecutor.execute(() -> {
            while (true) {
                if (atomicInteger.get() % 3 == 0) {
                    if (atomicInteger.get() == 99) {
                        countDownLatch.countDown();
                        atomicInteger.getAndIncrement();
                        break;
                    }
                    System.out.println("thread3    " + atomicInteger.get());
                    atomicInteger.getAndIncrement();
                }
            }
        });
        threadPoolExecutor.execute(() -> {
            while (true) {
                if (atomicInteger.get() % 3 == 1) {
                    if (atomicInteger.get() == 97) {
                        countDownLatch.countDown();
                        atomicInteger.getAndIncrement();
                        break;
                    }
                    System.out.println("thread1    " + atomicInteger.get());
                    atomicInteger.getAndIncrement();
                }
            }
        });
        threadPoolExecutor.execute(() -> {
            while (true) {
                if (atomicInteger.get() % 3 == 2) {
                    if (atomicInteger.get() == 98) {
                        countDownLatch.countDown();
                        atomicInteger.getAndIncrement();
                        break;
                    }
                    System.out.println("thread2    " + atomicInteger.get());
                    atomicInteger.getAndIncrement();
                }
            }
        });

        countDownLatch.await();

        threadPoolExecutor.shutdown();


    }

}
