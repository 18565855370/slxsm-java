package com.slxsm.threadpool.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo1 {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() ->{
            System.out.println(System.currentTimeMillis() + "开始执行");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "执行完成");
        },2, TimeUnit.SECONDS);
        executorService.shutdown();
    }
}
