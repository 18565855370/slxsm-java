package com.slxsm.boot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture 是对future的一个增强
 * 提供了四个静态创建一个异步方法
 * 如果指定了线程池Executor的话就用指定的，如果没有指定则使用FoolJoinPool.commonPool()作为线程池
 * runnable 这俩没有返回值
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor)
 * supplier 有返回值
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor)
 */
public class Demo1 {

    /*
     * 无返回值的runAsync
     */
    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future =
            CompletableFuture.runAsync(
                () -> {
                  try {
                    TimeUnit.SECONDS.sleep(1);
                      System.out.println("SUDO...");
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
        });
        future.get();
    }

    public static void supplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future =
            CompletableFuture.supplyAsync(
                () -> {
                  try {
                    TimeUnit.SECONDS.sleep(1);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                    System.out.println("run end...");
                  return System.currentTimeMillis();
        });
        long time = future.get();
        System.out.println("time=" + time);
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    runAsync();
    supplyAsync();
  }
}
