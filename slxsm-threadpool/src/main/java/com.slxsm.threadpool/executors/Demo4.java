package com.slxsm.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定时任务异常，则内部生吞了异常，日志不会抛异常报错，但是任务结束执行，所以异常需要开发人员自己处理，否则哈哈哈
 */
public class Demo4 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "任务初始化");
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() ->{
            System.out.println(System.currentTimeMillis() + "任务开始执行");
            try{
                TimeUnit.SECONDS.sleep(2);
                System.out.println(count.getAndIncrement()/0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "任务结束执行");
        },2,3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(5);
        System.out.println(future.isCancelled());//true代表取消任务执行，false否知
        System.out.println(future.isDone());//true代表已执行完成
    }
}
