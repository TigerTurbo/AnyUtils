package com.yshuoo.anyutils.parallelcomputing;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;


/**
 * 任务工具类
 * Copyright (c) 2000-2017 携程旅行网
 * all rights reserved.
 *
 * @author mingyuliu@Ctrip.com
 * date: 2017/2/15
 */
@Slf4j
public final class TaskUtil {

    private final static int RESPONSE_STEP_NUMBER = 1;


    public static <V> V await(Future<V> task, String name) {
        try {
            return task.get(); // NOSONAR
        } catch (Exception e) {
            log.error("{} task have error is {}",name,e);
        }
        return null;
    }

    public static int parallelism(ForkJoinPool pool, int length) {
        int parallelism = pool.getParallelism();
        int stepLen = length / parallelism + 1;
        return Math.max(stepLen, 1);
    }

    public static int parallelismResponse(ForkJoinPool pool, int length) {
        int parallelism = pool.getParallelism();
        int stepLen = length / parallelism + 1;
        return Math.max(stepLen, RESPONSE_STEP_NUMBER);
    }

    private TaskUtil() {

    }

}
