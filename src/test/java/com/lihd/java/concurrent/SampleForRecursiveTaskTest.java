package com.lihd.java.concurrent;

import com.lihd.java.concurrent.forkjoin.SampleForRecursiveTask;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-07 09:52
 **/
public class SampleForRecursiveTaskTest {


    @Test
    public void test(){

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        final SampleForRecursiveTask sampleForRecursiveTask = new SampleForRecursiveTask("dahsiuhfdesiufhgisdgfysgfiusgfseugfys");

        forkJoinPool.execute(sampleForRecursiveTask);

        sampleForRecursiveTask.join();



    }


}
