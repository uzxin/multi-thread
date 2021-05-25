package com.demo.multithread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2021/5/25 10:33
 * @version: 1.0
 * @description: 异步配置
 */
@Configuration
@EnableAsync
@Slf4j
public class AsyncConfig implements AsyncConfigurer {

    public static final String THREAD_POOL_EXECUTOR_NAME = "MyThreadPoolExecutor";
    public static final String EXECUTOR_NAME = "MytaskExecutor";
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 20;
    private static final int QUEUE_CAPACITY = 50;
    private static final int KEEP_ALIVE_SECONDS = 60;
    private static final String THREAD_NAME_PREFIX = THREAD_POOL_EXECUTOR_NAME + "-";

    @Bean(name = EXECUTOR_NAME)
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数，线程池创建时候初始化的线程数
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //最大线程数,线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //队列中最大的数目,用来缓冲执行任务的队列
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        //线程名字前缀
        taskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        //当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize
        // 如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
        //ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 默认策略
        //ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
        //ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
        //ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后的最大存活时间
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        //初始化
        taskExecutor.initialize();
        log.info("初始化线程池,名字:{},核心线程数:{},最大线程数:{},队列最大数:{},线程名称前缀:{},线程最空闲时间:{}",
                EXECUTOR_NAME,CORE_POOL_SIZE,MAX_POOL_SIZE,QUEUE_CAPACITY,THREAD_NAME_PREFIX,KEEP_ALIVE_SECONDS);
        return taskExecutor;
    }

    /**
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
    **/
    @Bean("MyThreadPoolExecutor")
    public ThreadPoolExecutor getExecutorService(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5,
                10,
                100,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10));
        log.info("自定义ThreadPoolExecutor初始化完毕,name:{}", THREAD_POOL_EXECUTOR_NAME);
        return threadPoolExecutor;
    }

    /**
     * 异常处理
    **/
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
