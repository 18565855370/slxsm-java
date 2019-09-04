package com.slxsm.boot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 如果当前线程依赖上个线程，使之变为串行执行,异常则不执行thenApply或thenApplyAsync
 * public <U> CompletableFuture<U> thenApply(Funnction<? super T, ? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Funnction<? super T, ? extends U> fn)
 * public <U> CompletableFuture<U> thenApplyAsync(Funnction<? super T, ? extends U fn>, Executor executor)
 */
public class Demo3 {

    public static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                long result = new Random().nextInt(100);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " result= " + result);
                return result;
            }
        }).thenApply(new Function<Long, Long>() {
            @Override
            public Long apply(Long aLong) {
                long result = aLong * 5;
                System.out.println(Thread.currentThread().getName() + " result2= " + result);
                return result;
            }
        });
        long result = future.get();
        System.out.println(result);
    }

    public static void thenApplyAsync(){
    CompletableFuture<Long> future =
        CompletableFuture.supplyAsync(
                new Supplier<Long>() {
                  @Override
                  public Long get() {
                    long result = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " result1= " + result);
                    return result;
                  }
                })
            .thenApplyAsync(
                new Function<Long, Long>() {
                  @Override
                  public Long apply(Long aLong) {
                    long result = aLong * 5;
                    System.out.println(Thread.currentThread().getName() + " result2= " + result);
                    return result;
                  }
                });
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //thenApply();
      thenApplyAsync();
      TimeUnit.SECONDS.sleep(4);
  }
}
