package com.slxsm.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal只能解决当前的线程变量问题，但是线程中再启动子线程的话是没有办法拿到父线程的相关参数，
 * 所以此处可以使用InHeritableThreadLocal来处理这个问题
 *
 * 装逼必会
 */
public class Demo4 {

    static InheritableThreadLocal<String> traceIdKd = new InheritableThreadLocal<>();

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
        CountDownLatch cdl = new CountDownLatch(dataList.size());
        String threadName = Thread.currentThread().getName();
        log("执行数据库操作");
        //模拟插入数据到数据库
        for (String s : dataList) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    log("插入数据" + s + "成功,主线程：" + threadName);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    cdl.countDown();
                }
            }).start();
        }
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
