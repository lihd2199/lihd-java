package com.lihd.java.reference.week;

import java.lang.ref.WeakReference;

/**
 * @program: lihd-java
 * @description: Sample For Weak Reference
 * @author: li_hd
 * @create: 2020-05-13 11:08
 **/
public class SampleForWeakReference extends WeakReference<Apple> {

    public SampleForWeakReference(Apple referent) {

        super(referent);

    }


    public void sample() throws InterruptedException {

        System.out.println(this.get());

        System.gc();

        Thread.sleep(5000);

        if (this.get() == null) {
            System.out.println("clear apple ");
        }

    }

}


