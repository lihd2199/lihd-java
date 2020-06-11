package com.lihd.java.concurrent;


import com.lihd.java.concurrent.executors.ExecutorsTest;


/**
 * @author lihd
 */
public class DeadLock {

    public static void main(String[] args)  {


        ExecutorsTest executors = ExecutorsTest.getInstance();

        Object a = 1;
        Object b = 2;

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                        System.out.println("已经获取a锁，即将获取b锁!");
                        b();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void b() {
                synchronized (b) {
                    System.out.println("b锁被占用!");
                }
            }

        };



        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                        System.out.println("已经获取b锁，即将获取a锁!");
                        a();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void a() {
                synchronized (a) {
                    System.out.println("a锁被占用!");
                }
            }
        };


        executors.execute(r1);
        executors.execute(r2);

    }



}







