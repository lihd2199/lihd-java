package com.lihd.java.concurrent.threadpool;


import java.util.HashSet;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @author: li_hd
 * @date: 2020-09-10 09:54
 **/
public class CoreThreadPool implements Executor {

    private final BlockingQueue<Runnable> workQueue;
    private final int coreSize;
    private int threadCount = 0;
    private HashSet<Worker> workerSet = new HashSet<>();

    public CoreThreadPool(int coreSize) {
        this.coreSize = coreSize;
        this.workQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void execute(Runnable command) {
        if (++threadCount <= coreSize) {
            final Worker worker = new Worker(command);
            worker.thread.start();
            workerSet.add(worker);
        } else {
            try {
                workQueue.put(command);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class Worker implements Runnable {

        private final Runnable firstTask;
        private final Thread thread;

        public Worker(Runnable runnable) {
            this.firstTask = runnable;
            thread = new Thread(this);
        }

        @Override
        public void run() {
            Runnable task = this.firstTask;
            while (null != task || null != (task = getTask())) {
                try {
                    task.run();
                } finally {
                    task = null;
                }
            }
        }

    }


    private Runnable getTask() {
        try {
            return workQueue.take();
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }


    public static void main(String[] args) throws InterruptedException {

        CoreThreadPool pool = new CoreThreadPool(5);

        final CountDownLatch countDownLatch = new CountDownLatch(100);

        IntStream.range(0, 100).forEach(i -> pool.execute(() -> {
            System.out.println(String.format("Thread:%s,value:%d", Thread.currentThread().getName(), i));
            countDownLatch.countDown();
        }));

        countDownLatch.await();

        System.out.println(pool.workerSet.size());


    }


}

