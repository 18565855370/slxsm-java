package com.slxsm.threadresult;

import java.util.concurrent.*;

/**
 * ExecutorService.submit
 */
public class Demo3 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //创建一个线程池
      ExecutorService executor = Executors.newCachedThreadPool();
      System.out.println(System.currentTimeMillis());
      Future<Integer> future = executor.submit(() -> {
         try{
             TimeUnit.SECONDS.sleep(3);
         }catch (InterruptedException e){
             e.printStackTrace();
         }
         return 10;
      });
      //关闭线程池
      executor.shutdown();
      System.out.println(System.currentTimeMillis());
      Integer result = future.get();
      System.out.println(System.currentTimeMillis() + ":" + result);
  }
}
