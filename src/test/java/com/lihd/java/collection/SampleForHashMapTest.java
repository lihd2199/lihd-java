package com.lihd.java.collection;

import org.junit.Assert;
import org.junit.Test;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-06-11 11:00
 **/
public class SampleForHashMapTest {

    @Test
    public void test_threadSafe() throws InterruptedException {

        final SampleForHashMap sampleForHashMap = new SampleForHashMap();
        final Integer mapSize = sampleForHashMap.threadSafe(500);
        System.out.println(mapSize);
        Assert.assertNotEquals(mapSize.intValue(), 500);

    }


}
