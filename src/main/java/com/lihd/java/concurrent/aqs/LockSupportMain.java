package com.lihd.java.concurrent.aqs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.locks.LockSupport;

/**
 * @author: li_hd
 * @date: 2020-09-10 14:39
 **/
public class LockSupportMain implements Runnable {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private Thread thread;

    private void setThread(Thread thread) {
        this.thread = thread;
    }

    public static void main(String[] args) throws Exception {
        LockSupportMain main = new LockSupportMain();
        Thread thread = new Thread(main);
        main.setThread(thread);
        thread.start();
        Thread.sleep(2000);
        main.unpark();
        Thread.sleep(2000);
    }

    @Override
    public void run() {
        System.out.println(String.format("%s-步入run方法,线程名称:%s", FORMATTER.format(LocalDateTime.now()),
                Thread.currentThread().getName()));
        LockSupport.park();
        System.out.println(String.format("%s-解除阻塞,线程继续执行,线程名称:%s", FORMATTER.format(LocalDateTime.now()),
                Thread.currentThread().getName()));
    }

    private void unpark() {
        LockSupport.unpark(thread);
    }
}
