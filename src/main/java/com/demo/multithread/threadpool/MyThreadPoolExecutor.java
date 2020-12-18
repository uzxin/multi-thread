package com.demo.multithread.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2020/12/18 16:06
 * @version: 1.0
 * @description: 最原始的创建线程池的方式，一共7个参数
 * 参数1.corePoolSize：核心线程数，线程池中始终存活的线程数
 * 参数2.maximumPoolSize：最大线程数，线程池中允许的最大线程数，当线程池的任务队列满了之后可以创建的最大线程数
 * 参数3.keepAliveTime：最大线程数可以存活的时间，当线程中没有任务执行时，最大线程就会销毁一部分，最终保持核心线程数量的线程
 * 参数4.unit：时间单位，是和keepAliveTime配合使用的，合在一起用于设定线程的存活时间
 * 参数5.workQueue：一个阻塞队列，用来存储线程池等待执行的任务，均为线程安全，它包含以7种类型
 * 参数6.threadFactory：线程工厂，主要用来创建线程，默认为正常优先级、非守护线程
 * 参数7.handler：拒绝策略，拒绝处理任务时的策略，系统提供了4种可选
 *
 * 【强制要求】线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 * 说明：Executors 返回的线程池对象的弊端如下：
 * 1） FixedThreadPool 和 SingleThreadPool：允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM。
 * 2）CachedThreadPool：允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
 */
public class MyThreadPoolExecutor {

    public static void main(String[] args) {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        // 执行任务
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threadPool.execute(() -> {
                System.out.println(index + " 被执行,线程名:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
