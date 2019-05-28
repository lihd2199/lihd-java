package com.lihd.java.synchronize;

import com.lihd.java.executors.ExecutorsTest;

/**
 * @author lihd
 * @desc 线程间通信  两个线程打印出121212
 */
public class ThreadCommunication {


    public static void main(String[] args) {

        Communication communication1 = new Communication();
        communication1.setInteger(1);
        communication1.setLock("abc");
        ExecutorsTest.getInstance().execute(communication1);
        Communication communication2 = new Communication();
        communication2.setInteger(2);
        communication2.setLock("abc");
        ExecutorsTest.getInstance().execute(communication2);
        ExecutorsTest.getInstance().shutdown();
        System.exit(0);
    }


}

class Communication implements Runnable{

    private String lock;
    private Integer integer;
    void setInteger(Integer integer) {
        this.integer = integer;
    }
    public void setLock(String lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

        for(int i=0;i<3;i++){
            synchronized (lock){
                lock.notify();
                System.out.println(integer);
                System.out.println(Thread.currentThread().getName());
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
