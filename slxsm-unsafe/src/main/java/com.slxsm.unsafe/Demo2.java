package com.slxsm.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 借助(反射)Unsafe实现AtomicInteger的getAndIncrement
 */
public class Demo2 {

    static Unsafe unsafe;
    //用来记录每次访问量，每访问一次+1
    static int count;
    //count在Demo2.class对象中的地址偏移量
    static long countOffset;

    static {
        try {
            //获取Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);

            Field countField = Demo2.class.getDeclaredField("count");
            //获取count字段在demo2中的内存地址的偏移量
            countOffset = unsafe.staticFieldOffset(countField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //模拟一次操作
    public static void request() throws InterruptedException {
        //模拟耗时5秒
        TimeUnit.MILLISECONDS.sleep(5);
        //count原子加一
        unsafe.getAndAddInt(Demo2.class,countOffset,1);
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        final CountDownLatch cdl = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread t = new Thread(() -> {
               try{
                   for (int j = 0; j < 10; j++) {
                       request();
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
        System.out.println(Thread.currentThread().getName() + ",耗时：" + (endTime - startTime) + ",count: " + count);
    }
}
