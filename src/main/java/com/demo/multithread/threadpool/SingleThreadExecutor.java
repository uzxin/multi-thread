package com.demo.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 15:58
 * @version: 1.0
 * @description: 单个线程数的线程池，它可以保证先进先出的执行顺序；
 */
public class SingleThreadExecutor {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + ":任务被执行");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
    }
}
