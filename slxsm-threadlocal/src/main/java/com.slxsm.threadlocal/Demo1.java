package com.slxsm.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 普通通过线程池执行业务代码（controller -> service -> dao）
 *
 * 这种写法不利于查找每个请求的执行链路，不利于开发
 */
public class Demo1 {

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
        System.out.println("****" + System.currentTimeMillis() + ",[线程:" + Thread.currentThread().getName() + "]," + stack[1] + ":" + msg);
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
            executor.submit(() -> {
               controller(dataList);
            });
        }
        executor.shutdown();
    }
}
