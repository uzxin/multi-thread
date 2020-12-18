package com.demo.multithread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 15:47
 * @version: 1.0
 * @description: 固定大小的线程池，可控制并发的线程数，超出的线程会在队列中等待；
 */
public class FixedThreadPool {

    public static void main(String[] args) {
        // 创建2个数据级的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        // 创建任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("任务被执行,线程:" + Thread.currentThread().getName());
            }
        };
        // 线程池执行任务(一次添加 4 个任务)
        // 执行任务的方法有两种:submit 和 execute
        // 区别1、接收的参数不一样。exucute只能执行实现Runnable接口的线程，submit可以执行实现Runnable接口或Callable接口的线程
        // 区别2、submit有返回值，而execute没有
        threadPool.submit(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
        threadPool.execute(runnable);
    }
}
