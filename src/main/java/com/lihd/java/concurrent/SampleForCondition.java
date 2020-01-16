package com.lihd.java.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: lihd-java
 * @description: sample for condition
 * @author: li_hd
 * @create: 2019-12-23 14:37
 **/
public class SampleForCondition extends Thread {

    private static ReentrantLock lock = new ReentrantLock();

    private Integer integer;

    private Integer count = 0;

    private Condition condition;

    private SampleForCondition(Integer integer, Condition condition) {
        this.integer = integer;
        this.condition = condition;
    }


    public static void main(String[] args) {

        Condition condition = lock.newCondition();

        Thread thread1 = new SampleForCondition(1, condition);
        Thread thread2 = new SampleForCondition(2, condition);

        thread1.start();
        thread2.start();


    }

    @Override
    public void run() {
        try {
            while (true) {
                lock.lock();
                condition.signal();
                System.out.println(integer);
                condition.await();
                if (count++ == 100) {
                    return;
                }
                lock.unlock();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}

