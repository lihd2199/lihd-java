package com.lihd.java.concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-12 11:37
 **/
public class SampleForLockTest {

    @Test
    public void test() {

        ReentrantLock lock = new ReentrantLock();

        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 2000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    lock.lock();
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
        }

        threadPoolExecutor.shutdown();

    }


}
