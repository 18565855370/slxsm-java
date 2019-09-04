package com.slxsm.guava;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * guava是google的一个包，部分是对juc包的一些扩展
 * ListeningExecutorServer接口继承了ExecutorServer接口，提供了监听器功能
 * MoreExecutors 是对juc的Executors的扩展
 * Futures 是对juc的Future的一个扩展
 */
public class Demo1 {
  public static void main(String[] args) {
    //创建一个线程池
     ExecutorService executor = Executors.newFixedThreadPool(5);
     try{
         ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);
         //异步执行一个任务
         ListenableFuture<Integer> submit = executorService.submit(() -> {
            System.out.println(String.format("%s",System.currentTimeMillis()));
            //休眠2秒，默认耗时
             try {
                 TimeUnit.SECONDS.sleep(2);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println(String.format("%s",System.currentTimeMillis()));
             return 10;
         });
         System.out.println(submit.get());
         //当任务执行完毕之后回调对应的方法
         submit.addListener(() -> {
             try {
                 TimeUnit.SECONDS.sleep(5);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             System.out.println("任务执行完毕了，我被回调了");
         },MoreExecutors.directExecutor());
     } catch (InterruptedException | ExecutionException e) {
         e.printStackTrace();
     } finally{
        executor.shutdown();
     }
     System.out.println("----------------------");
  }
}
