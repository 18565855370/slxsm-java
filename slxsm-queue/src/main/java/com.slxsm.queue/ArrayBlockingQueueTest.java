package com.slxsm.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue 是一个有界的FIFO可是公平锁的阻塞队列，内部是由数组组成，由put和take添加和移除消息，
 * 如果生产的速度远大于消费速度，这样一旦队列满了就会阻塞后续线程，这样会造成严重后果，
 * 通过构造函数设置fair为true是公平队列（先到的一定先出），但是由于内部是ReenTrantLock实现，采用的同一个锁，所以无法分开生产和消费
 */
public class ArrayBlockingQueueTest {

    private static ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(10000);

    static {
    new Thread(
        () -> {
          while (true) {
            String msg;
            try {
              long startTime = System.currentTimeMillis();
              msg = queue.take();
                long endTime = System.currentTimeMillis();
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息：%s",startTime,endTime,(endTime - startTime),Thread.currentThread().getName(),msg));
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }).start();
    }

    public static void pushMsg(String msg) throws InterruptedException {
        queue.put(msg);
    }

  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 5; i++) {
        String msg = "一起来玩，第" + i + "天";
        //TimeUnit.SECONDS.sleep(i);
        pushMsg(msg);
    }
  }
}
