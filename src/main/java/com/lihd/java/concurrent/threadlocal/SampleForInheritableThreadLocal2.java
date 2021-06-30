package com.lihd.java.concurrent.threadlocal;

public class SampleForInheritableThreadLocal2 extends Thread {

    static final String VALUE01 = "VALUE01";
    static final String VALUE02 = "VALUE02";


    InheritableThreadLocal<String> inheritableThreadLocal;

    public SampleForInheritableThreadLocal2(InheritableThreadLocal<String> inheritableThreadLocal) {
        super();

        this.inheritableThreadLocal = inheritableThreadLocal;
    }

    public static void main(String[] args) throws InterruptedException {


        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(VALUE01);

        Thread thread = new SampleForInheritableThreadLocal2(inheritableThreadLocal);
        thread.setName("Thread01");
        thread.start();
        thread.join();

        Thread thread2 = new SampleForInheritableThreadLocal2(inheritableThreadLocal);
        thread2.setName("Thread02");
        thread2.start();
        thread2.join();


        System.out.println(Thread.currentThread().getName() + "******************************************");
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "******************************************");
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());

        inheritableThreadLocal.set(VALUE02);

        System.out.println(Thread.currentThread().getName() + "(Reset Value)*****************************");
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());
    }


}
