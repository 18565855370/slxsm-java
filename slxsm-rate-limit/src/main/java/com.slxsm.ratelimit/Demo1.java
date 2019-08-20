package com.slxsm.ratelimit;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 通过控制并发量来进行限流,下面示例采用的是信号量的方式 semaphore
 */
public class Demo1 {

    private static Semaphore semaphore = new Semaphore(5);

  public static void main(String[] args) {
    for (int i = 0; i < 20; i++) {
      new Thread(
              () -> {
                boolean flag = false;
                try {
                  flag = semaphore.tryAcquire(100, TimeUnit.MILLISECONDS);
                  if (flag) {
                    // 模拟下单操作耗时
                    System.out.println(Thread.currentThread() + ",尝试下单中......");
                    TimeUnit.SECONDS.sleep(2);
                  } else {
                    System.out.println(Thread.currentThread() + ",秒杀失败，请稍后重试");
                  }
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }finally{
                    if (flag){
                        semaphore.release();
                    }
                }
              })
          .start();
    }
  }
}
