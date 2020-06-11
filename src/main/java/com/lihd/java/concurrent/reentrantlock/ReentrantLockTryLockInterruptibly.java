package com.lihd.java.concurrent.reentrantlock;



import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihd
 * @desc ReentrantLockTryLock
 */
public class ReentrantLockTryLockInterruptibly {


    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ReentrantLockTryLockInterruptibly lockLook = new ReentrantLockTryLockInterruptibly();
        Runnable runnable = () -> {
            try {
                lockLook.insert();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();

    }

    public void insert() throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(Thread.currentThread().getName() + "得到了锁");
            Thread.sleep(10000);
        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }


}
