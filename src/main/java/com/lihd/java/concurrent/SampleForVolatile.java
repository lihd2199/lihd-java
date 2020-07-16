package com.lihd.java.concurrent;

/**
 * @author: li_hd
 * @date: 2020-07-16 17:23
 **/
public class SampleForVolatile {

    //测试一
    private static Boolean isOk = true;
    //测试二
//    private static volatile Boolean isOk = true;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isOk = false;
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("开始循环了");
            while (isOk) {

            }
            System.out.println("跳出循环了");
        });
        thread1.start();
        thread2.start();
        thread1.join();
        System.out.println(isOk);
    }


}
