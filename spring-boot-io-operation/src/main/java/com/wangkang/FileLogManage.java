package com.wangkang;

import com.wangkang.normal.BufferedFileLog;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description:
 * @Author: wangkang
 * @Date: Created in 19:51 2019/12/5
 * @Modified By:
 */
public class FileLogManage {

    private static final FileLog NOOP_FILE_LOG = new NoOpFileLog();
    private static Map<String, FileLog> map = new HashMap<>();
    private static String dateString = "2019-11-11";

    public static FileLog fileLog() {
        String threadFileName = getThreadFileName();
        FileLog fileLog = map.get(threadFileName);
        if (fileLog != null) {
            return fileLog;
        }

        synchronized (FileLogManage.class) {
            FileLog log;
            try {
                log = getFileLog(threadFileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                log = NOOP_FILE_LOG;
            }
            map.put(threadFileName, log);
            return log;
        }
    }

    private static FileLog getFileLog(String threadFileName) throws FileNotFoundException {

        return new BufferedFileLog(threadFileName);
    }

    private static String getThreadFileName() {
        return Thread.currentThread().getName() + dateString;
    }

    //定时任务，更新 dateString 和 map
    private void updateDateStringAndMap() {

    }

    //单独线程读
    public static ExecutorService getExecutorService() {
        return executorService;
    }
    private static int queueSize = 20000;
    private static ExecutorService executorService = new ThreadPoolExecutor(1, 1,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueSize),getThreadFactory(),getRejectedExecutionHandler());

    private static ThreadFactory getThreadFactory() {
        return threadFactory;
    }
    private static RejectedExecutionHandler getRejectedExecutionHandler() {
        return rejectedExecutionHandler;
    }
    private static RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("这里做一些统计");
        }
    };

    private static ThreadFactory threadFactory = new ThreadFactory() {
        private final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "filelog-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    };
}
