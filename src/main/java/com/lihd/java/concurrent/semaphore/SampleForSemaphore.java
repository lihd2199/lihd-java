package com.lihd.java.concurrent.semaphore;


import java.util.concurrent.Semaphore;


public class SampleForSemaphore {

    public static void main(String[] args) {


        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "开始");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "获取到信号量");
                    Thread.sleep(1000);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "释放信号量");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
