package com.slxsm.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类一共分为四大类:
 * 一是原子基本类型(atomicInteger,atomicLong,atomicBoolean)
 * 二是原子数组类型(atomicIntegerArray,atomicLongArray,atomicReferenceArray)
 * 三是原子引用类型(atomicReference,atomicStampedReference,atomicMarkableReference)
 * 四是对象的属性修改原子类(atomicIntegerFieldUpdate,atomicLongFieldUpdate,atomicReferenceFieldUpdate)
 *
 * 本示例演示atomicInteger，其他俩基本类型的操作类型
 */
public class Demo1 {

    //访问次数（原子的）
    static AtomicInteger count = new AtomicInteger();

    public static void request() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        //对count原子的加一
        count.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch cdl = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
               try {
                   for (int j = 0; j < 10; j++) {
                       request();
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
        System.out.println(Thread.currentThread().getName() + ",耗时：" + (endTime - startTime) + ",count：" + count);
    }
}
