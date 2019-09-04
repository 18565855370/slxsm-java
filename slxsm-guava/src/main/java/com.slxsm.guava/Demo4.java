package com.slxsm.guava;

import com.google.common.util.concurrent.*;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 获取结果，在回调任务中计算
 */
public class Demo4 {

  public static void main(String[] args) {
      System.out.println("start");
      ExecutorService executor = Executors.newFixedThreadPool(5);
      try{
          ListeningExecutorService executorService = MoreExecutors.listeningDecorator(executor);
          List<ListenableFuture<Integer>> futureList = new ArrayList<>();
          for (int i = 5; i >= 0; i--){
              int j = i;
              futureList.add(executorService.submit(() -> {
                  try {
                      TimeUnit.SECONDS.sleep(2);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  return j;
              }));
          }
          ListenableFuture<List<Integer>> listListenableFuture = Futures.allAsList(futureList);
          Futures.addCallback(listListenableFuture, new FutureCallback<List<Integer>>() {
              @Override
              public void onSuccess(@Nullable List<Integer> results) {
                  System.out.println("result中所有结果之和: " + results.stream().reduce(Integer::sum).get());
              }

              @Override
              public void onFailure(Throwable throwable) {
                  System.out.println("执行任务发生异常: " + throwable.getMessage());
              }
          },MoreExecutors.directExecutor());
      }finally{
          executor.shutdown();
      }
  }
}
