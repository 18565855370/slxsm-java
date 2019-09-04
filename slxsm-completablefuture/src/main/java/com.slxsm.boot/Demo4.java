package com.slxsm.boot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * handle 对结束后的任务进行计算执行handle值直接执行任务，handleAsync是异步执行任务
 * public <U> CompletableStage<U> handle(BiFunction<? super T, Throwable, ? extends U> fn)
 * public <U> CompletableStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn)
 * public <U> CompletableStage<U> handleAsync(BiFunction<? super T, Throwable, ? extends U> fn, Executor executor)
 */
public class Demo4 {

    //handle
    private static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                //int i = 10/0;
                return new Random().nextInt(10);
            }
        }).handle(new BiFunction<Integer, Throwable, Integer>() {
            @Override
            public Integer apply(Integer integer, Throwable throwable) {
                int result = -1;
                if (throwable == null){
                    return integer * 2;
                }else {
                    System.out.println(throwable.getMessage());
                }
                return result;
            }
        });
        System.out.println(future.get());
    }

    private static void handleAsync() throws ExecutionException, InterruptedException {
    CompletableFuture<Integer> future =
        CompletableFuture.supplyAsync(
                new Supplier<Integer>() {
                  @Override
                  public Integer get() {
                    System.out.println(Thread.currentThread().getName() + "-thread1");
                    int i = 10 / 0;
                    return new Random().nextInt(10);
                  }
                })
            .handleAsync(
                new BiFunction<Integer, Throwable, Integer>() {
                  @Override
                  public Integer apply(Integer integer, Throwable throwable) {
                    int result = -1;
                      try {
                          TimeUnit.SECONDS.sleep(5);
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      System.out.println(Thread.currentThread().getName() + "-thread2");
                    if (throwable == null) {
                      return integer * 2;
                    } else {
                      System.out.println(throwable.getMessage());
                    }
                    return result;
                  }
                });
        System.out.println(future.get());
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println(Thread.currentThread().getName());
    handle();
    handleAsync();
  }
}
