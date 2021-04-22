package com.lihd.java.concurrent.threadlocal;

public class SampleForInheritableThreadLocal  extends Thread {

    static final String VALUE01 = "VALUE01";
    static final String VALUE02 = "VALUE02";


    ThreadLocal<String> threadLocal;
    InheritableThreadLocal<String> inheritableThreadLocal;

    public SampleForInheritableThreadLocal(ThreadLocal<String> threadLocal, InheritableThreadLocal<String> inheritableThreadLocal) {
        super();
        this.threadLocal = threadLocal;
        this.inheritableThreadLocal = inheritableThreadLocal;
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set(VALUE01);

        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(VALUE01);

        Thread thread = new SampleForInheritableThreadLocal(threadLocal, inheritableThreadLocal);
        thread.setName("Thread01");
        thread.start();
        thread.join();

        Thread thread2 = new SampleForInheritableThreadLocal(threadLocal, inheritableThreadLocal);
        thread2.setName("Thread02");
        thread2.start();
        thread2.join();


        System.out.println(Thread.currentThread().getName() + "******************************************");
        System.out.println(Thread.currentThread().getName() + "\tThreadLocal: " + threadLocal.get());
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + "******************************************");
        System.out.println(Thread.currentThread().getName() + "\tThreadLocal: " + threadLocal.get());
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());

        threadLocal.set(VALUE02);
        inheritableThreadLocal.set(VALUE02);

        System.out.println(Thread.currentThread().getName() + "(Reset Value)*****************************");
        System.out.println(Thread.currentThread().getName() + "\tThreadLocal: " + threadLocal.get());
        System.out.println(Thread.currentThread().getName() + "\tInheritableThreadLocal: " + inheritableThreadLocal.get());
    }


}
