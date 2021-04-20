package com.lihd.java.concurrent.semaphore;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;


public class SampleForSemaphore {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < 10; i++) {

            new Thread(() -> {
                try {
//                    用state作为指标，通过cas每次acquire，减少一个，release释放一个
                    System.out.println(Thread.currentThread().getName() + "开始");
//                    semaphore.acquire();
//                    可以指定时间
//                    LockSupport.parkNanos();
                    boolean b = semaphore.tryAcquire(1, 1000L, TimeUnit.MILLISECONDS);
                    if (b) {
                        System.out.println(Thread.currentThread().getName() + "获取到信号量");
                        Thread.sleep(10000);
                        semaphore.release();
                        System.out.println(Thread.currentThread().getName() + "释放信号量");
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获取超时");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
