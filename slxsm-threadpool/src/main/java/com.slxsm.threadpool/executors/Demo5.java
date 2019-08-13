package com.slxsm.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过scheduledFuture取消线程执行（cancel（false））
 */
public class Demo5 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println(System.currentTimeMillis() + "任务初始化");
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        ScheduledFuture future = executorService.scheduleWithFixedDelay(() -> {
            long currCount = count.getAndIncrement();
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次任务开始执行");
            try{
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次任务结束执行");
        },2,3, TimeUnit.SECONDS);
        TimeUnit.SECONDS.sleep(2);
        //当参数为true，而且当前线程还未执行完成，则会中断线程，任务取消成功:java.lang.InterruptedException: sleep interrupted
        System.out.println(future.cancel(true));//true表示打断执行，false表示执行完成当前，返回false表示不能取消代表这个任务已经执行完成
        TimeUnit.SECONDS.sleep(1);
        System.out.println("任务是否被取消：" + future.isCancelled());
        System.out.println("任务十分执行完成：" + future.isDone());
    }
}
