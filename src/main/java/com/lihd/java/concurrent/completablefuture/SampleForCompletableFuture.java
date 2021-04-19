package com.lihd.java.concurrent.completablefuture;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-04-23 19:07
 **/
public class SampleForCompletableFuture {


    @Test
    public void test() throws InterruptedException {

        final ThreadFactory threadFactory = r -> {
            final DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory("assddd");
            final Thread thread = defaultThreadFactory.newThread(r);
            thread.setName(String.valueOf(System.currentTimeMillis()));
            return thread;
        };


        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), threadFactory);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
            return 1 + 1;
        }, threadPoolExecutor);

        future.whenComplete((v, e) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(v);
        });


        System.out.println(1);

        new CountDownLatch(1).await();

    }


    @Test
    public void test2() {

        List<CompletableFuture<Integer>> list = new ArrayList<>();

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            int finalI = i;
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000* finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
                result.add(1000* finalI);
                return null;
            });
            list.add(future);

        }



        final CompletableFuture[] completableFutures = list.toArray(new CompletableFuture[0]);
        CompletableFuture.allOf(completableFutures).join();

        System.out.println(result);

    }


}
