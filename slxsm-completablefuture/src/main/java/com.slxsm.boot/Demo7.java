package com.slxsm.boot;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * 任务合并，将多个执行的CompletionStage 执行完成后的结果合并
 * public <U,V> CompletionStage<V> thenCombine(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn)
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn)
 * public <U,V> CompletionStage<V> thenCombineAsync(CompletionStage<? extends U> other, BiFunction<? super T, ? super U, ? extends V> fn, Executor, executor)
 */
public class Demo7 {

    private static void thenCombine() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello...";
            }
        });
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello...";
            }
        });
        CompletableFuture<String> result = future.thenCombine(future1, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        });
        System.out.println(result.get());
    }

    public static void thenCombineAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello...";
            }
        });
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "hello...";
            }
        });
        CompletableFuture<String> result = future.thenCombineAsync(future1, new BiFunction<String, String, String>() {
            @Override
            public String apply(String s, String s2) {
                return s + s2;
            }
        });
        System.out.println(result.get());
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //thenCombine();
    thenCombineAsync();
  }
}
