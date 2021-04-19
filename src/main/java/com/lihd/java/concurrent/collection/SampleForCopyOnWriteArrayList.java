package com.lihd.java.concurrent.collection;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-04-16 10:45
 **/
public class SampleForCopyOnWriteArrayList {


    @Test
    public void test() throws InterruptedException {

        CopyOnWriteArrayList<Integer> integers = new CopyOnWriteArrayList<>();

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        new Thread(() -> integers.forEach(e -> {

            System.out.println(e);
            try {
                if (e == 3)
                    cyclicBarrier.await();
                    Thread.sleep(1000);
            } catch (InterruptedException | BrokenBarrierException ex) {
                ex.printStackTrace();
            }
        })).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            final boolean add = integers.add(5);
            System.out.println(add);
        }).start();

        new CountDownLatch(1).await();


    }


}
