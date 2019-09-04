package com.slxsm.counter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class Demo2 {

    static AtomicLong count = new AtomicLong(0);

    public static void incr(){
        count.incrementAndGet();
    }

    private static void m2() throws InterruptedException {
        long t1 = System.currentTimeMillis();
        int threadCount = 50;
        CountDownLatch cdl = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try{
                    for (int j = 0; j < 1000000; j++){
                        incr();
                    }
                }finally{
                    cdl.countDown();
                }
            }).start();
        }
        cdl.await();
        long t2 = System.currentTimeMillis();
        System.out.println(String.format("结果：%s,耗时(ms): %s",count,(t2 - t1)));
    }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 10; i++) {
        count.set(0);
        m2();
    }
  }
}
