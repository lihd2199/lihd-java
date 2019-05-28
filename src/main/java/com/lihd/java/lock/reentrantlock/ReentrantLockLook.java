package com.lihd.java.lock.reentrantlock;

import com.lihd.java.executors.ExecutorsTest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lihd
 * @desc lock 应用
 */
public class ReentrantLockLook {

    private  Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockLook lockLook = new ReentrantLockLook();
        Runnable runnable = lockLook::lockTest;
        ExecutorsTest.getInstance().execute(runnable);
        ExecutorsTest.getInstance().execute(runnable);
        ExecutorsTest.getInstance().shutdown();
    }


    private void lockTest(){

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"-start");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"-end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }



}



