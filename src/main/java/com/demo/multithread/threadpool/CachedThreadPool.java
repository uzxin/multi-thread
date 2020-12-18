package com.demo.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 15:53
 * @version: 1.0
 * @description: 可缓存的线程池，若线程数超过处理所需，缓存一段时间后会回收，若线程数不够，则新建线程；
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        // 创建线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 执行任务
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
