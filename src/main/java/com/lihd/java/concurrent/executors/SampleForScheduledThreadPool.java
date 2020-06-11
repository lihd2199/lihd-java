package com.lihd.java.concurrent.executors;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: lihd-java
 * @description: Sample For ScheduledThreadPool
 * @author: li_hd
 * @create: 2020-04-16 11:27
 **/
public class SampleForScheduledThreadPool {


    @Test
    public void test() throws InterruptedException {


        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        System.out.println(System.currentTimeMillis());

        final CountDownLatch countDownLatch = new CountDownLatch(1);


        executor.schedule(() -> {

            System.out.println(System.currentTimeMillis());
            countDownLatch.countDown();

        }, 3000, TimeUnit.MILLISECONDS);


        countDownLatch.await();

    }





    @Test
    public void test2() throws InterruptedException {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);

        executor.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,3,TimeUnit.SECONDS);

        new CountDownLatch(1).await();

    }



    @Test
    public void test3() throws InterruptedException {


        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

        executorService.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },0,3,TimeUnit.SECONDS);

        new CountDownLatch(1).await();

    }



}
