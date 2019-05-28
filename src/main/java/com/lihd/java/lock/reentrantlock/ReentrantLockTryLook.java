package com.lihd.java.lock.reentrantlock;


import com.lihd.java.executors.ExecutorsTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihd
 * @desc tryLook
 */
public class ReentrantLockTryLook {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ReentrantLockTryLook test = new ReentrantLockTryLook();
        Runnable runnable1 = test::insert;
        Runnable runnable2 = test::insert;

        ExecutorsTest.getInstance().execute(runnable1);
        ExecutorsTest.getInstance().execute(runnable2);
        ExecutorsTest.getInstance().shutdown();


    }

    private void insert() {

        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName() + "得到了锁");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "释放了锁");
                lock.unlock();
            }
        } else {
            System.out.println(Thread.currentThread().getName() + "获取锁失败");
        }


    }


}
