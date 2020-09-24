package com.lihd.java.concurrent;

import com.lihd.java.concurrent.forkjoin.SampleForRecursiveTask;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-05-07 09:52
 **/
public class SampleForRecursiveTaskTest {


    @Test
    public void test() throws ExecutionException, InterruptedException {

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        SampleForRecursiveTask countTask = new SampleForRecursiveTask(1, 100);

        ForkJoinTask<Integer> result = forkJoinPool.submit(countTask);

        System.out.println("result: " + result.get());

        forkJoinPool.shutdown();

    }


}
