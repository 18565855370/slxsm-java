package com.slxsm.threadpool.rethreadpool;

import java.lang.management.ManagementFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义ThreadFactory
 */
public class Demo4 {

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
                4,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10), r -> {
            Thread thread = new Thread(r);
            thread.setName("自定义线程-" + atomicInteger.getAndIncrement());
            return thread;
        });
        for (int i = 0; i < 4; i++) {
            String taskName = "任务" + i;
            executor.execute(() ->{
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
            });
        }
        executor.shutdown();
    }
}
