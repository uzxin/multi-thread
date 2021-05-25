package com.demo.multithread.util;

import com.demo.multithread.config.AsyncConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2021/5/25 11:55
 * @version: 1.0
 * @description:
 */
@Component
public class ThreadPoolUtil {

    @Resource(name = AsyncConfig.EXECUTOR_NAME)
    private Executor executor;

    @Resource(name = AsyncConfig.THREAD_POOL_EXECUTOR_NAME)
    private ThreadPoolExecutor threadPoolExecutor;

    private static ThreadPoolUtil threadPoolUtil;

    @PostConstruct
    public void init() {
        threadPoolUtil = this;
        threadPoolUtil.executor = this.executor;
        threadPoolUtil.threadPoolExecutor = this.threadPoolExecutor;
    }

    public static void execute(Runnable task){
        threadPoolUtil.executor.execute(task);
    }

    public static<T> Future<T> execute(Callable<T> callable){
        return threadPoolUtil.threadPoolExecutor.submit(callable);
    }

}
