package com.slxsm.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁和非公平锁演示，
 * 公平锁：锁之间不存在争抢状态，而是先到先得
 * 非公平锁：线程争抢线程，没有先来后到之分
 *
 * ReentrantLock可以实现公平锁和非公平锁，构造函数参数为false时是非公平锁，是true的时候是公平锁，默认是非公平锁
 */
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
       //非公平锁
       test1(false);
       TimeUnit.SECONDS.sleep(4);
        System.out.println("---------------------------------");
       //公平锁
       test1(true);
    }

    private static void test1(boolean fair) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(fair);
        Thread testThread1 = new Thread(() -> {
           lock.lock();
           try{
               System.out.println(Thread.currentThread().getName() + " start");
               TimeUnit.SECONDS.sleep(3);
               new Thread(() -> {
                   m1(lock,"son");
               }).start();
               System.out.println(Thread.currentThread().getName() + " end");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }finally{
               lock.unlock();
           }
        });
        testThread1.setName("test");
        testThread1.start();
        TimeUnit.SECONDS.sleep(1);
        m1(lock,"father");
    }

    //10个线程，每个线程都获取一个锁
    private static void m1(final ReentrantLock lock, String threadPre){
        for (int i = 0; i < 10; i++) {
            Thread threadM1 = new Thread(() -> {
                lock.lock();
                try{
                    System.out.println(Thread.currentThread().getName() + " 获取锁了");
                }finally{
                    lock.unlock();
                }
            });
            threadM1.setName(threadPre + "-" + i);
            threadM1.start();
        }
    }
}
