package com.demo.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 16:03
 * @version: 1.0
 * @description: 抢占式执行的线程池（任务执行顺序不确定）
 */
public class WorkStealingPool {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService threadPool = Executors.newWorkStealingPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
            });
        }
        // 确保任务执行完成
        while (!threadPool.isTerminated()) {
        }
    }
}
