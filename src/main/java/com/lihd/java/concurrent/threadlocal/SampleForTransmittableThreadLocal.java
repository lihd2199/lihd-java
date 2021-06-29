package com.lihd.java.concurrent.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SampleForTransmittableThreadLocal {


    static TransmittableThreadLocal<Integer> transmittableThreadLocal = new TransmittableThreadLocal<>();


    static InheritableThreadLocal<Integer> integerInheritableThreadLocal = new InheritableThreadLocal<>();


    static ExecutorService executor = Executors.newFixedThreadPool(5);


    static CountDownLatch countDownLatch1 = new CountDownLatch(10);


    static CountDownLatch countDownLatch2 = new CountDownLatch(10);


    public static void main(String[] args) throws InterruptedException {


        for (int i = 0; i < 10; i++) {

            integerInheritableThreadLocal.set(i);

            transmittableThreadLocal.set(i);

            new Thread(() -> {

                executor.execute(() -> {

                    System.out.println(Thread.currentThread().getName() + "    " + integerInheritableThreadLocal.get() + "    " + transmittableThreadLocal.get());

                    countDownLatch1.countDown();
                });

            }).start();

        }

        countDownLatch1.await();

        System.out.println("==============================================");

        for (int i = 0; i < 10; i++) {

            integerInheritableThreadLocal.set(i);

            transmittableThreadLocal.set(i);

            new Thread(() -> {

                executor.execute(Objects.requireNonNull(TtlRunnable.get(() -> System.out.println(Thread.currentThread().getName() + "    " + integerInheritableThreadLocal.get() + "    " + transmittableThreadLocal.get()))));

                countDownLatch2.countDown();

            }).start();

        }

        countDownLatch2.await();

        executor.shutdown();

    }


}
