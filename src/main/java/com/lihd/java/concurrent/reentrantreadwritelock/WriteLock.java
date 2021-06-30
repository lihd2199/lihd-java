package com.lihd.java.concurrent.reentrantreadwritelock;

import com.lihd.java.concurrent.executors.ExecutorsTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lihd
 * @desc 写锁
 */
public class WriteLock {

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {

        readAndRead();
//        readAndWrite();
//        writeAndRead();
//        writeAndWrite();

    }

    public static void readAndRead() throws InterruptedException {
        read();
        Thread.sleep(1000L);
        read();
    }

    public static void readAndWrite() throws InterruptedException {
        read();
        Thread.sleep(1000L);
        write();
    }

    public static void writeAndRead() throws InterruptedException {
        write();
        Thread.sleep(1000L);
        read();
    }

    public static void writeAndWrite() throws InterruptedException {
        write();
        Thread.sleep(1000L);
        write();
    }


    public static void read() {

        new Thread(() -> {
            try {
                lock.readLock().lock();
                System.out.println(Thread.currentThread().getName() + "获取读锁!");
                new CountDownLatch(1).await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }

        }).start();
    }

    public static void write() {

        new Thread(() -> {
            ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
            try {
                writeLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取读锁!");
                new CountDownLatch(1).await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                writeLock.unlock();
            }

        }).start();
    }



}




