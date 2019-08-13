package com.slxsm.threadpool.rethreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue队列
 */
public class Demo2 {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 1; i < 14; i++) {
            int j = i;
            String taskName = "任务" + j;
            service.execute(() ->{
                System.out.println(Thread.currentThread().getName() + "处理" + taskName);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

}
