package com.slxsm.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * Unsafe类的MonitorEnter和MonitorExit方法，加锁、释放锁，必须成对出现
 */
public class Demo4 {

    static int count;

    static Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //模拟访问
    public static void request(){
        unsafe.monitorEnter(Demo4.class);
        try {
            count++;
        }finally {
            unsafe.monitorExit(Demo4.class);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=================");
        long startTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch cdl = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                }finally {
                    cdl.countDown();
                }
            });
            thread.start();
        }
        cdl.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - startTime) + ",count=" + count);
    }
}
