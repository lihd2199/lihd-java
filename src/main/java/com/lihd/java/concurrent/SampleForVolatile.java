package com.lihd.java.concurrent;

/**
 * @author: li_hd
 * @date: 2020-07-16 17:23
 **/
public class SampleForVolatile {

    //测试一
//    private static Boolean isOk = true;
    //测试二
    private static volatile Boolean isOk = true;

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
            try {
                System.out.println("开始循环了");
                while (isOk) {
//                    加上三句这句话，可跳出循环
//                    1、IO操作切换线程，导致线程缓存失效
//                    2、synchronized 强制刷新缓存
//                    3、当一个线程终止时，所有写入的变量都被刷新到主内存。例如:
//                    现有线程A，B，在B线程中调用A.join()，那么在B中可以保证看到A线程产生的影响。
//                    File file = new File("G://1.txt");
//                    System.out.println(Thread.currentThread().getName() + isOk);
//                    thread1.join();
                }
                System.out.println("跳出循环了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        System.out.println(isOk);
    }


}
