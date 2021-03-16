package com.yshuoo.anyutils.parallelcomputing;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * ForkJoinPool并行计算
 * @author yangshuo
 * @date 2021/3/16 15:16
 */
public class ComputingAction extends RecursiveTask<Long> {

    final int lo, hi;
    final int step;
    final long[] map;
    final AtomicInteger bodyIndex;

    ComputingAction(int lo, int hi, int step, long[] map, AtomicInteger bodyIndex){
        this.lo = lo;
        this.hi = hi;
        this.step = step;
        this.map = map;
        this.bodyIndex = bodyIndex;
    }

    @Override
    protected Long compute() {
        int currentStep = hi - lo;
        if (currentStep > this.step) {
            int mid = (lo + hi) >> 1;
            ComputingAction task1 = new ComputingAction(lo, mid, this.step, map, bodyIndex);
            ComputingAction task2 = new ComputingAction(mid + 1, hi, this.step, map, bodyIndex);
            invokeAll(task1, task2);
            return task1.join() + task2.join();
        }
        long sum = 0L;
        for (int i = lo; i <= hi; i++) {
            // 业务逻辑
            int index = bodyIndex.getAndIncrement();
            sum += i;
            map[index] = sum;
        }
        return sum;
    }
}
