package com.slxsm.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * Unsafe类的park和unpark操作（阻塞和唤醒线程）
 */
public class Demo3 {

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

    public static void m1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start");
            //b:是否是绝对时间，i是阻塞时间，纳秒时间
            unsafe.park(false,0);
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end");
        });
        thread.setName("thread1");
        thread.start();

        TimeUnit.SECONDS.sleep(5);
        unsafe.unpark(thread);
        System.out.println("thead1");
    }

    public static void m2() {
        Thread thread = new Thread(() -> {
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",start");
            //b:是否是绝对时间，i是阻塞时间，纳秒时间
            unsafe.park(false,TimeUnit.SECONDS.toNanos(3));
            System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",end");
        });
        thread.setName("thread2");
        thread.start();
        System.out.println("thead2");

    }

    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
    }
}
