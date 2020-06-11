package com.lihd.java.reference;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-13 14:00
 **/
public class SampleForWeakReferenceTest {

    @Test
    public void test_sample() throws InterruptedException {

        final SampleForWeakReference sampleForWeakReference = new SampleForWeakReference(new Apple("12343"));

        sampleForWeakReference.sample();

        Assert.assertNull(sampleForWeakReference.get());

    }


}
