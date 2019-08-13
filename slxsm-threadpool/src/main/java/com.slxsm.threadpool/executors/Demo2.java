package com.slxsm.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * scheduleThreadPoolExecutod的scheduleAtFixedRate固定执行频率，这个方法以频率为主，也就是说间隔2秒执行一次，但是一次任务
 * 执行了三秒，那么下一个线程在任务执行完成后直接执行，如果任务执行了两秒但是间隔为3秒，那么在任务结束后的一秒执行
 *
 * 多观察执行结果
 */
public class Demo2 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        //任务执行计数器
        AtomicInteger count = new AtomicInteger(1);
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        service.scheduleAtFixedRate(() ->{
           int currCount = count.getAndIncrement();
            System.out.println(Thread.currentThread().getName());
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(2);
                if (currCount == 3){
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "第" + currCount + "次" + "结束执行");
        },10,4, TimeUnit.SECONDS);
        System.out.println("主线程执行完成");
    }
}
