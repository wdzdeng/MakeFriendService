package com.wdzdeng.it.friend.util;

import java.util.concurrent.*;

/**
 * 线程池工具类
 * @author wdz
 * @version 1.0
 * @date 2019/12/25 9:55
 */
public class MatchThreadPoolUtil {
    private static final Integer QUEUE_SIZE = 5;
    private static final Integer CORE_POOL_SIZE = 1;
    private static final Integer MAX_POOL_SIZE = 3;
    private static final Long KEEP_ALIVE_TIME = 0L;
    private static  ExecutorService threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(QUEUE_SIZE),
            Thread::new,
            new ThreadPoolExecutor.AbortPolicy());
    public static void match(Runnable command){
        threadPool.execute(command);
    }

}
