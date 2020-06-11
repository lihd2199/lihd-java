package com.lihd.java.concurrent;


import com.lihd.java.concurrent.executors.ExecutorsTest;

/**
 * @author lihd
 * @desc synchronize 应用
 */
public class Synchronize {


    public static void main(String[] args) {

        Thread thread1 = new MyThread();
        Thread thread2 = new MyThread();
        ExecutorsTest.getInstance().execute(thread1);
        ExecutorsTest.getInstance().execute(thread2);
        ExecutorsTest.getInstance().shutdown();

    }


}


class MyThread extends Thread {

    @Override
    public void run() {
        try {
            thread();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void thread() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "-start");
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "-end");
    }


}
