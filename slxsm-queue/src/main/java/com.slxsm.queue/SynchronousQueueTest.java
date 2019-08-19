package com.slxsm.queue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * synchronousQueue是一个同步队列，队列不存储任何数据，来一个消费一个，如果工作线程都忙那就新建线程，用的不多，Executors.newCacheThreadPool()使用了此队列
 *
 * 也可以使用构造函数使他为公平队列
 */
public class SynchronousQueueTest {

    static SynchronousQueue<String> queue = new SynchronousQueue<>();

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
        long startTime = System.currentTimeMillis();
        try {
            queue.put("slxsm是帅哥");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println(String.format("[%s,%s,take耗时:%s],%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName()));
    }).start();
      //休眠5秒之后，从队列中take一个元素
      TimeUnit.SECONDS.sleep(5);
      System.out.println(System.currentTimeMillis() + "调用take获取并移除元素," + queue.take());
  }
}
