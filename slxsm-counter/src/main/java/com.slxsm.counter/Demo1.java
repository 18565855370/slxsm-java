package com.slxsm.counter;

import java.util.concurrent.CountDownLatch;

public class Demo1 {

    static int count = 0;

    public static synchronized void incr(){
        count++;
    }

    public static void m1() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch cdl = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try{
                    for (int i1 = 0; i1 < 100; i1++) {
                        incr();
                    }
                }finally{
                    cdl.countDown();
                }
            }).start();
        }
        cdl.await();
        long t2 = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + String.format(" 结果：%s,耗时(ms):%s",count,(t2-t1)));
    }

  public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i< 10; i++){
            m1();
        }
  }
}
