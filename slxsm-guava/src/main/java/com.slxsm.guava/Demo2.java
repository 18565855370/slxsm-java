package com.slxsm.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demo1是使用ListenableFuture.addListener添加监听器，任务执行完毕后执行添加到监听器里面的代码
 * 本类使用了Futures.addCallback实现异步回调
 */
public class Demo2 {

  public static void main(String[] args) {
      ExecutorService executor = Executors.newFixedThreadPool(5);
      try{
          ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);
          ListenableFuture<Integer> submit = executorService.submit(() -> {
              System.out.println(String.format("%s",System.currentTimeMillis()));
              try {
                  TimeUnit.SECONDS.sleep(4);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              //如果模拟异常情况，则去掉注释
              //int i = 10 / 0;
              System.out.println(String.format("%s",System.currentTimeMillis()));
              return 10;
          });
          Futures.addCallback(submit, new FutureCallback<Integer>() {
              @Override
              public void onSuccess(@Nullable Integer integer) {
                  System.out.println(String.format("执行成功:%s",System.currentTimeMillis()));
              }

              @Override
              public void onFailure(Throwable throwable) {
                try{
                    TimeUnit.MILLISECONDS.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println("执行任务发生异常: " + throwable.getMessage());
              }
          },MoreExecutors.directExecutor());
      }finally{
          executor.shutdown();
      }
  }
}
