package com.slxsm.boot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/**
 * thenRun不关心上个任务的结果，直接执行本方法内的结果
 * public CompletionStage<Void> thenRun(Runnable action)
 * public CompletionStage<Void> thenRunAsync(Runnable action)
 * public CompletionStage<Void> thenRunAsync(Runnable action, Executor executor)
 */
public class Demo6 {
    public static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Random().nextInt(10);
            }
        }).thenRun(() -> {
           System.out.println("thenRun...");
        });
        future.get();
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    thenRun();
  }
}
