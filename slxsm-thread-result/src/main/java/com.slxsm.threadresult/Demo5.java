package com.slxsm.threadresult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * futureTask另外一种方法
 */
public class Demo5 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println(System.currentTimeMillis());
    //创建一个futureTask
      FutureTask<Integer> futureTask = new FutureTask<>(() -> 10);
      new Thread(() -> {
          try {
              TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          futureTask.run();
      }).start();

    System.out.println(System.currentTimeMillis());
    //futureTask.get会阻塞当前线程，知道所有futuretask执行完毕
      Integer result = futureTask.get();
      System.out.println(System.currentTimeMillis() + ":" + result);
  }
}
