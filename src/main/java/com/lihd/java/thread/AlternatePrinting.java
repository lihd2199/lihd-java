package com.lihd.java.thread;

/**
 * @program: lihd-java
 * @description: Alternate Printing
 * @author: li_hd
 * @create: 2020-01-15 14:50
 **/
public class AlternatePrinting {

    public static void main(String[] args) {

        Object obj = new Object();
        MyThread myThread1 = new MyThread(obj,1);
        MyThread myThread2 = new MyThread(obj,2);
        myThread1.start();
        myThread2.start();

    }


}


class MyThread extends Thread {

    final private Object object;

    private Integer integer;

    MyThread(Object obj, Integer integer) {

        this.object = obj;
        this.integer = integer;

    }

    @Override
    public void run() {

        for (int i=0;i<10;i++) {
            synchronized (object) {
                object.notify();
                System.out.println(integer);
                try {
                    object.wait();
                } catch (InterruptedException ignore) {

                }
            }
        }

    }
}
