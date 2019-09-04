package com.slxsm.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 获取异步执行结果,按照任务执行的顺序返回
 */
public class Demo3 {

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
                      TimeUnit.MILLISECONDS.sleep(100);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  return j;
              }));
          }
          //获取一批任务的执行结果
          List<Integer> resultList = Futures.allAsList(futureList).get();
          //输出
          resultList.forEach(item -> {
              System.out.println(String.format("%s",item));
          });
      } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
      } finally{
          executor.shutdown();
      }
  }
}
