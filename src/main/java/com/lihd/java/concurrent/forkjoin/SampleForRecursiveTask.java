package com.lihd.java.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author: li_hd
 * @date: 2020-09-24 13:44
 **/
public class SampleForRecursiveTask extends RecursiveTask<Integer> {

    private final int start;

    private final int end;

    public SampleForRecursiveTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < 6) {
            // 当任务很小时，直接进行计算
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            System.out.println(Thread.currentThread().getName() + " count sum: " + sum);
        } else {

            // 否则，将任务进行拆分
            int mid = (end - start) / 2 + start;
            SampleForRecursiveTask left = new SampleForRecursiveTask(start, mid);
            SampleForRecursiveTask right = new SampleForRecursiveTask(mid + 1, end);

            // 执行上一步拆分的子任务
            left.fork();
            right.fork();

            // 拿到子任务的执行结果
            sum += left.join();
            sum += right.join();
        }

        return sum;
    }


}
