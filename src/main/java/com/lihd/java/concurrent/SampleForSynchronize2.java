package com.lihd.java.concurrent;

/**
 * @author: li_hd
 * @date: 2020-08-20 15:41
 **/
public class SampleForSynchronize2 {

    private final Object object = new Object();

    public synchronized void test1(){

        System.out.println(1);

    }

    public void test2(){

        synchronized (object){

            System.out.println(2);

        }
    }


}
