package com.lihd.java.thread;

/**
 * @program: lihd-java
 * @description: sample for join
 * @author: li_hd
 * @create: 2020-01-15 16:18
 **/
public class SampleForJoin extends Thread{


    public static void main(String[] args) throws InterruptedException {

        SampleForJoin sampleForJoin = new SampleForJoin();
        sampleForJoin.start();
        sampleForJoin.join();
        System.out.println("----------------main end");


    }


    @Override
    public void run() {

        System.out.println("--------------------------thread start");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignore) {

        }

        System.out.println("--------------------------thread end");

    }
}
