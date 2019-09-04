package com.slxsm.boot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * 执行任务并且不返回值，thenAccept继续计算
 */
public class Demo5 {

    public static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt();
            }
        }).thenAccept(integer -> {
            System.out.println(integer);
        });
        future.get();
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    thenAccept();
  }
}
