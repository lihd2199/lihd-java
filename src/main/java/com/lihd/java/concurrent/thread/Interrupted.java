package com.lihd.java.concurrent.thread;

import org.junit.Test;


/**
 * @author: li_hd
 * @date: 2020-08-16 19:36
 **/
public class Interrupted {

    @Test
    public void interrupt() {

        int stop = 0;

        while (stop++ < 1000) {
            System.out.println(Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }

        System.out.println(1);

    }


}
