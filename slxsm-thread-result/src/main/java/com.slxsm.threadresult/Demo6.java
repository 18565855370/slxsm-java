package com.slxsm.threadresult;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 */
public class Demo6 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println(System.currentTimeMillis());
      CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
          try {
              TimeUnit.SECONDS.sleep(3);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          return new Random().nextInt(10);
      });
    System.out.println(System.currentTimeMillis());
    //CompletableFuture.get()会阻塞当前线程，知道futureTask执行完毕
      Integer result = future.get();
    System.out.println(System.currentTimeMillis() + ":" + result);
  }
}
