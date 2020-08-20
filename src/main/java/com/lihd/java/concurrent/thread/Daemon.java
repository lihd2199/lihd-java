package com.lihd.java.concurrent.thread;


/**
 * @author: li_hd
 * @date: 2020-08-16 19:22
 **/
public class Daemon {

    public static void main(String[] args) {

        final Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //main 结算后整个线程就结束了
        //当在一个JVM进程里面开多个线程时，这些线程被分成两类：守护线程和非守护线程。
        // 默认开的都是非守护线程。
        // 在Java中有一个规定：当所有的非守护线程退出后，整个JVM进程就会退出。
        thread.setDaemon(true);
        thread.start();

        System.out.println("main exit");

    }


}
