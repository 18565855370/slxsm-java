package com.slxsm.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Demo4 {

    public static void main(String[] args) throws InterruptedException {
        //非公平锁
        test1(false);
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() + " ------------------------------");
        //公平锁
        test1(true);
    }

    public static void test1(boolean fair) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(fair);
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                TimeUnit.SECONDS.sleep(3);
                new Thread(() -> {
                    m1(lock, "son");
                }).start();
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t1.setName("t1");
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        m1(lock, "father");
    }

    public static void m1(ReentrantLock lock, String threadPre) {
        for (int i = 0; i < 30; i++) {
            Thread thread = new Thread(() -> {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " 获取到了锁");
                } finally {
                    lock.unlock();
                }
            });
            thread.setName(threadPre + "-" + i);
            thread.start();
        }
    }
}
