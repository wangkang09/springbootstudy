package com.wangkang.context;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author wangkang
 * @date: 2019-08-12
 */
public class ThreadPoolUtil {
    public static ThreadPoolExecutor myThreadPoolExecutor = new ThreadPoolExecutor(8,20, 30, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(50));

}
