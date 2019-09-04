package com.slxsm.boot;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * ApplyToEither
 * 将两个结果，那个返回的快，就使用CompletionStage进行下一步操作,applyToEither有返回值，acceptEither无返回值
 * public <U> CompletionStage<U> applyToEither(CompletionStage<? extends T> other,Function<? super T,U> fn)
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T,U> fn)
 * public <U> CompletionStage<U> applyToEitherAsync(CompletionStage<? extends T> other,Function<? super T,U> fn, Executor executor)
 *
 */
public class Demo9 {

    public static void applyToEither() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try{
                    TimeUnit.SECONDS.sleep(3);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("f1= " + t);
                return t;
            }
        });
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int t = new Random().nextInt(3);
                try{
                    TimeUnit.SECONDS.sleep(t);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("f2= " + t);
                return t;
            }
        });
        CompletableFuture<Integer> result = future.applyToEither(future1, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                System.out.println(integer);
                return integer * 2;
            }
        });
        System.out.println(result.get());
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    applyToEither();
  }
}
