package com.slxsm.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 数组原子类，具体分类兼Demo1.class
 *
 * 操作数组中每个下标的数
 * main，耗时：894
 * 第1个页面访问次数为1000
 * 第2个页面访问次数为1000
 * 第3个页面访问次数为1000
 * 第4个页面访问次数为1000
 * 第5个页面访问次数为1000
 * 第6个页面访问次数为1000
 * 第7个页面访问次数为1000
 * 第8个页面访问次数为1000
 * 第9个页面访问次数为1000
 * 第10个页面访问次数为1000
 */
public class Demo2 {

    static AtomicIntegerArray count = new AtomicIntegerArray(new int[10]);

    //模拟一次请求
    public static void request(int page) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        int pageCountIndex = page;
        count.getAndIncrement(pageCountIndex);
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch cdl = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
               try{
                   for (int page = 0; page < 10; page++) {
                       for (int j = 0; j < 10; j++) {
                           request(page);
                       }
                   }
               }catch (InterruptedException e){
                   e.printStackTrace();
               }finally {
                   cdl.countDown();
               }
            });
            thread.start();
        }
        cdl.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - startTime));
        for (int pageIndex = 0; pageIndex < 10; pageIndex++) {
            System.out.println("第" + (pageIndex + 1) + "个页面访问次数为" + count.get(pageIndex));
        }
    }
}
