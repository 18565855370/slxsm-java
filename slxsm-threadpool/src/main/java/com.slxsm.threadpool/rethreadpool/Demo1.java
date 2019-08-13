package com.slxsm.threadpool.rethreadpool;

import java.util.concurrent.*;

/**
 * 最简单的线程池是哟
 */
public class Demo1 {

    /**
     * 这里代表一次提交的线程只能有15个线程，否则则抛出异常（Exception in thread "main" java.util.concurrent.RejectedExecutionException: 。。。）
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            5,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int j = i;
            String taskName = "任务" + j;
            executor.execute(() ->{
                try {
                    TimeUnit.SECONDS.sleep(j);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + taskName + "处理完毕！");
            });
        }
        executor.shutdown();
    }
}
