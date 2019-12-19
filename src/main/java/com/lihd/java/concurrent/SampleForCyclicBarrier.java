package com.lihd.java.concurrent;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @program: lihd-java
 * @description: sample for cyclic barrier
 * @author: 黎
 * @create: 2019-12-19 23:38
 **/
class SampleForCyclicBarrier {


    private static final Integer NUM_WORKERS = 5;

    private static final Integer NUM_PARTIAL_RESULTS = 15;


    @Test
    public void test() {

        List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());

        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread(partialResults));

        List<Thread> list = Stream.generate(() -> new Thread(new NumberCruncherThread(cyclicBarrier, partialResults),String.valueOf(System.currentTimeMillis()))).limit(NUM_WORKERS).collect(Collectors.toList());

        list.forEach(Thread::start);

    }


    class NumberCruncherThread implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private List<List<Integer>> partialResults;

        NumberCruncherThread(CyclicBarrier cyclicBarrier, List<List<Integer>> partialResults) {
            this.cyclicBarrier = cyclicBarrier;
            this.partialResults = partialResults;
        }

        private Random random = new Random();

        @Override
        public void run() {

            String thisThreadName = Thread.currentThread().getName();

            List<Integer> partialResult = new ArrayList<>();

            // Crunch some numbers and store the partial result
            for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
                Integer num = random.nextInt(10);
                System.out.println(thisThreadName
                        + ": Crunching some numbers! Final result - " + num);
                partialResult.add(num);
            }

            partialResults.add(partialResult);
            try {
                System.out.println(thisThreadName
                        + " waiting for others to reach barrier." + cyclicBarrier.getNumberWaiting());
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException ignore) {

            }


        }
    }


    class AggregatorThread implements Runnable {

        AggregatorThread(List<List<Integer>> partialResults) {
            this.partialResults = partialResults;
        }

        private List<List<Integer>> partialResults;

        @Override
        public void run() {

            String thisThreadName = Thread.currentThread().getName();

            System.out.println(
                    thisThreadName + ": Computing sum of " + NUM_WORKERS
                            + " workers, having " + NUM_PARTIAL_RESULTS + " results each.");
            int sum = 0;

            for (List<Integer> threadResult : partialResults) {
                System.out.print("Adding ");
                for (Integer partialResult : threadResult) {
                    System.out.print(partialResult + " ");
                    sum += partialResult;
                }
                System.out.println();
            }
            System.out.println(thisThreadName + ": Final result = " + sum);
        }
    }

}



