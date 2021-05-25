package com.demo.multithread.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @project: multi-thread
 * @author: youxin
 * @date: 2021/5/25 11:41
 * @version: 1.0
 * @description: 异步任务返回值解析
 */
public class FutureUtil<T> {

    private ArrayList<T> list = new ArrayList<>(16);

    public List<T> parsing(List<Future<T>> futures) {
        try {
            for (Future future : futures) {
                list.add((T) future.get());
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
