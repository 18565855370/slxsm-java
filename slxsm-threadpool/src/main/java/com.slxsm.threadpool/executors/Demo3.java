package com.slxsm.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 定时延迟执行，该方法是线程在initDeplay之后开始执行，执行完上一个线程需要等待deplay时间，这个时间是固定的，永远都是上次执行
 * 完成后再加上deplay时间
 */
public class Demo3 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() + "初始执行");
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> {
            System.out.println(System.currentTimeMillis() + "任务开始执行");
            try{
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "任务结束执行");
        },5,2, TimeUnit.SECONDS);
        System.out.println(future.isCancelled());
        System.out.println(future.isDone());
    }
}
