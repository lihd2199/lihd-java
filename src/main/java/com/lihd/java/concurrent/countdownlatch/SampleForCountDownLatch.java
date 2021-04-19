package com.lihd.java.concurrent.countdownlatch;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @program: lihd-java
 * @description: sample for CountDownLatch.class
 * @author: li_hd
 * @create: 2019-12-19 16:31
 **/
public class SampleForCountDownLatch {


    @Test
    void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion() throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(9000);
        List<Thread> workers =
                Stream.generate(() -> new Thread(new Worker(outputScraper, countDownLatch))).limit(9000).collect(toList());
        workers.forEach(Thread::start);
        countDownLatch.await();
        System.out.println(outputScraper.size());

    }


    @Test
    void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTim() throws InterruptedException {

        List<String> outputScraper = new ArrayList<>();
        CountDownLatch readyThreadCounter = new CountDownLatch(9000);
        CountDownLatch callingThreadBlocker = new CountDownLatch(1);
        CountDownLatch completedThreadCounter = new CountDownLatch(9000);
        List<Thread> workers =
                Stream.generate(() -> new Thread(new Worker2(outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter))).limit(9000).collect(toList());

        workers.forEach(Thread::start);

        readyThreadCounter.await();

        outputScraper.add("Workers ready");
        callingThreadBlocker.countDown();
        completedThreadCounter.await();
        outputScraper.add("Workers complete");


        System.out.println(outputScraper.size());

    }


    @Test
    void whenFailingToParallelProcess_thenMainThreadShouldGetNotGetStuck()
            throws InterruptedException {

        List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        List<Thread> workers = Stream
                .generate(() -> new Thread(new Worker3(outputScraper, countDownLatch)))
                .limit(5)
                .collect(toList());

        workers.forEach(Thread::start);

        countDownLatch.await();

       // countDownLatch.await(10000, TimeUnit.MILLISECONDS);

    }




}


class Worker implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        outputScraper.add("Counted down");
        countDownLatch.countDown();

    }
}

class Worker2 implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch readyThreadCounter;
    private CountDownLatch callingThreadBlocker;
    private CountDownLatch completedThreadCounter;

    public Worker2(List<String> outputScraper, CountDownLatch readyThreadCounter, CountDownLatch callingThreadBlocker, CountDownLatch completedThreadCounter) {
        this.outputScraper = outputScraper;
        this.readyThreadCounter = readyThreadCounter;
        this.callingThreadBlocker = callingThreadBlocker;
        this.completedThreadCounter = completedThreadCounter;
    }

    @Override
    public void run() {

        readyThreadCounter.countDown();
        try {
            callingThreadBlocker.await();
            outputScraper.add("Counted down");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            completedThreadCounter.countDown();
        }

    }
}


class Worker3 implements Runnable {

    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    Worker3(List<String> outputScraper, CountDownLatch countDownLatch) {
        this.outputScraper = outputScraper;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {

        try {
            if (true) {
                throw new RuntimeException("Oh dear, I'm a BrokenWorker");
            }
            outputScraper.add("Counted down");
        }finally {
            countDownLatch.countDown();
        }
    }
}


