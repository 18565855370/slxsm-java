package com.slxsm.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 由于demo不好分析每个链路，所以demo2增加了ThreadLocal提供了线程局部变量，独立于变量的初始化副本，
 * threadLocal希望将状态与线程相（事务或用户Id）关联
 */
public class Demo2 {

    static ThreadLocal<String> traceIdKd = new ThreadLocal<>();

    static AtomicInteger threadIndex = new AtomicInteger(1);
    //创建处理请求的线程池
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(3,
            3,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("executor-" + threadIndex.getAndIncrement());
                return thread;
            });

    //记录日志
    public static void log(String msg){
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        System.out.println("****" + System.currentTimeMillis() + ",[traceId:" + traceIdKd.get() + "],[线程:" + Thread.currentThread().getName() + "]," + stack[1] + ":" + msg);
    }

    //模拟controller
    public static void controller(List<String> dataList) {
        log("接受请求");
        service(dataList);
    }

    //模拟service
    public static void service(List<String> dataList) {
        log("执行业务");
        dao(dataList);
    }

    //模拟dao
    public static void dao(List<String> dataList) {
        log("执行数据库操作");
        //模拟插入数据
        for (String s : dataList) {
            log("插入数据" + s + "成功");
        }
    }

    public static void main(String[] args) {
        //需要插入的数据
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            dataList.add("数据" + i);
        }
        int requestCount = 5;
        for (int i = 0; i < requestCount; i++) {
            String traceId = String.valueOf(i);
            executor.submit(() -> {
                traceIdKd.set(String.valueOf(traceId));
                try{
                    controller(dataList);
                }finally {
                    traceIdKd.remove();
                }
            });
        }
        executor.shutdown();
    }
}
