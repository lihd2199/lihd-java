package com.lihd.java.reference.week;


import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-06-11 20:29
 **/
public class SampleForReferenceQueue {

    public void sampleForReferenceQueue() throws InterruptedException {

        ReferenceQueue<Apple> appleReferenceQueue = new ReferenceQueue<>();

        WeakReference<Apple> appleWeakReference1 = new WeakReference<>(new Apple("1"), appleReferenceQueue);

        WeakReference<Apple> appleWeakReference2 = new WeakReference<>(new Apple("2"), appleReferenceQueue);

        System.out.println("=====gc调用前======");

        Reference<? extends Apple> reference = null;

        while ((reference = appleReferenceQueue.poll()) != null) {
            System.out.println(reference.get());
        }

        System.out.println(appleWeakReference1);
        System.out.println(appleWeakReference2);

        System.out.println(appleWeakReference1.get());
        System.out.println(appleWeakReference2.get());

        System.out.println("=====gc调用======");
        System.gc();

        Thread.sleep(5000);

        System.out.println("=====gc调用后======");

        System.out.println(appleWeakReference1.get());

        System.out.println(appleWeakReference2.get());

        Reference<? extends Apple> reference2;
        while ((reference2 = appleReferenceQueue.poll()) != null) {
            System.out.println("appleReferenceQueue中：" + reference2);
        }


    }


}
