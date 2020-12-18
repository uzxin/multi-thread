package com.demo.multithread.threadpool;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 16:00
 * @version: 1.0
 * @description: 可以执行延迟任务的线程池
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        // 创建线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 添加定时执行任务(1s 后执行)
        System.out.println("添加任务,时间:" + new Date());
        threadPool.schedule(() -> {
            System.out.println("任务被执行,时间:" + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
        }, 10, TimeUnit.SECONDS);
    }
}
