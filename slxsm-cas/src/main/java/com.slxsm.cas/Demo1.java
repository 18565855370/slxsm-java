package com.slxsm.cas;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示存在线程安全的count++操作
 */
public class Demo1 {

    //访问次数
   /*static int count = 0;*/

   //synchronized修饰达到线程安全效果
   /* static Object object = new Object();
    private static void count() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        synchronized (object){
            count++;
        }
    }*/

    //reentrantlock加锁实现线程安全效果
    /*static ReentrantLock lock = new ReentrantLock();
    private static void count() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        lock.lock();
        count++;
        lock.unlock();
    }*/

    //cas操作
    /**
     *  i++可分为三步：1、获取i的值，2、对i的值加一，3、将加完的值写会到内存
     *  这里的casi只是做了第三步,因为锁优化也只是对++加锁，但是cas只是对++的最后一步操作，所以性能要比其他两种好一点
     */
    /*volatile static int count;
    private static synchronized boolean compareAndSwapInt(int expectValue, int newValue){
        if (getCount() == expectValue){
            count = newValue;
            return true;
        }
        return false;
    }

    private static int getCount(){
        return count;
    }

    private static void count() throws InterruptedException {
        //模拟操作5秒
        TimeUnit.MILLISECONDS.sleep(5);
        int expectValue;
        do {
            expectValue = getCount();
        }while (!compareAndSwapInt(expectValue,expectValue + 1));
    }*/

    //atomic原子访问次数
    static AtomicInteger count = new AtomicInteger();
    //模拟访问一次
    public static void count() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch cdl = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++){
            Thread t = new Thread(() -> {
               try{
                   for (int j = 0; j < 10; j++) {
                       count();
                   }
               }catch (InterruptedException e){
                   e.printStackTrace();
               }finally {
                   cdl.countDown();
                }
            });
            t.start();
        }
        cdl.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "耗时：" + (endTime - startTime) + ",count：" + count);
    }
}
