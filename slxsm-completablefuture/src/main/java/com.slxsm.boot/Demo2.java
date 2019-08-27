package com.slxsm.boot;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * 当CompletableFuture执行完毕之后（或内部执行抛出异常的时候），可以执行特定的Action
 * 共提供了四种方法
 * public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action) //执行完任务继续执行此操作(同一个线程)
 * public CompletableFuture<T> whenCompleteAsync(BiComsumer<? super T, ? super Throwable> action) //将这个操作丢到线程池中操作
 * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor) //将这个操作丢到线程池中操作，但是指定了线程池
 * public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> fn> 异常执行方法，用于处理异常
 */
public class Demo2 {

    public static void runAsync() throws Exception{
    CompletableFuture<Void> future =
        CompletableFuture.runAsync(
            () -> {
              try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " runAsync");
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              if (new Random().nextInt() % 2 >= 0) {
                int i = 12 / 0;
              }
              System.out.println(Thread.currentThread().getName() + " run end...");
            });
        //如果异常先执行exceptionally
        future.whenComplete(
            new BiConsumer<Void, Throwable>() {
              @Override
              public void accept(Void aVoid, Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + " 执行完毕");
              }
        });

        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + " 执行失败! " + throwable.getMessage());
                return null;
            }
        });
        System.out.println("main "  + Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(2);
    }

  public static void main(String[] args) throws Exception {
    runAsync();
  }
}
