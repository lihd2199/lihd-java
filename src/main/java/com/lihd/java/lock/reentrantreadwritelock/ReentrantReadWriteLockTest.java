package com.lihd.java.lock.reentrantreadwritelock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lihd
 * @desc 读写锁
 */
public class ReentrantReadWriteLockTest {


    public static void main(String[] args) {

        final Queue3 q3 = new Queue3();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    q3.get();
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    q3.put(new Random().nextInt(10000));
                }
            }).start();
        }

    }


}

class Queue3 {

    /**
     * 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
     */
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private Object data = null;

    void get() {
        rwl.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " be ready to read data!");
        try {
            Thread.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + "have read data :" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock(); //释放读锁，最好放在finally里面
        }

    }

    void put(Object data) {

        rwl.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " be ready to write data!");
        try {
            Thread.sleep((long) (Math.random() * 1000));
            this.data = data;
            System.out.println(Thread.currentThread().getName() + " have write data: " + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();//释放写锁
        }

    }
}
