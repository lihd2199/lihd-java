package com.lihd.java.thread;

/**
 * @program: lihd-java
 * @description: sample for yield
 * @author: li_hd
 * @create: 2020-01-14 16:49
 **/
public class SampleForYield extends Thread {


    private SampleForYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 50; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i == 30) {
                yield();
            }
        }
    }

    public static void main(String[] args) {
        SampleForYield yt1 = new SampleForYield("张三");
        SampleForYield yt2 = new SampleForYield("李四");
        yt1.start();
        yt2.start();

    }

}
