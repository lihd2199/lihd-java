package com.lihd.java.executors;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lihd
 * @desc 自定义线程池
 */
public class ExecutorsTest {

    private static ExecutorsTest executorsTest;

    static {
        executorsTest = new ExecutorsTest();
    }

    private ExecutorsTest() {
        executorService = new ThreadPoolExecutor(5, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new MyThreadFactory());
    }

    private static  ThreadPoolExecutor executorService ;

    public static ExecutorsTest getInstance() {
        return executorsTest;
    }

    public void execute(Runnable command) {
        if (command == null){
            throw new NullPointerException();
        }
        executorService.execute(command);
    }


    public <T> Future<T> submit(Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        RunnableFuture<T> task = new FutureTask<>(callable);
        execute(task);
        return task;
    }


    public int getBlockingThreadNun(){
        return executorService.getQueue().size();
    }


    public void shutdown(){
         executorService.shutdown();
    }

    static class MyThreadFactory implements ThreadFactory {
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            namePrefix = "thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,
                    namePrefix + threadNumber.getAndIncrement());
            return t;
        }
    }


    public static void main(String[] args) {

        for(int i=0;i<999;i++){
            ExecutorsTest.getInstance().execute(() -> System.out.println(Thread.currentThread().getName()));
        }
        ExecutorsTest.getInstance().shutdown();

    }


}
