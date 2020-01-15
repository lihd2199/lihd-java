package com.lihd.java.waitandnotify;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: lihd-java
 * @description: sample for wait and notify
 * @author: li_hd
 * @create: 2020-01-15 13:54
 **/
public class SampleForWaitAndNotify {

    public static void main(String[] args) throws InterruptedException {


        SampleForWaitAndNotify sampleForWaitAndNotify = new SampleForWaitAndNotify();
        //sampleForWaitAndNotify.test1();
        //sampleForWaitAndNotify.test2();
        //sampleForWaitAndNotify.test3();
        sampleForWaitAndNotify.test4();

    }

    //error  java.lang.IllegalMonitorStateException
    private void test1() throws InterruptedException {

        Object obj = new Object();
        obj.wait();
        obj.notifyAll();

    }

    private final Object o = new Object();

    //error java.lang.IllegalMonitorStateException
    private void test2() throws InterruptedException {


        final Object o1 = new Object();

        synchronized (o) {
            o1.wait();
        }
    }

    private void test3() throws InterruptedException {

        synchronized (o) {
            o.wait();
        }

    }

    private ReentrantLock reentrantLock = new ReentrantLock();

    //用condition
    //error IllegalMonitorStateException
    private void test4() throws InterruptedException {
        reentrantLock.lock();
        reentrantLock.wait();
    }


}
