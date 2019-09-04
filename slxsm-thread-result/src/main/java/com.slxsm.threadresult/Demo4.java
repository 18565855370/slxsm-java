package com.slxsm.threadresult;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask
 */
public class Demo4 {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    System.out.println(System.currentTimeMillis());
    //创建一个futureTask
      FutureTask<Integer> futureTask = new FutureTask<>(() -> {
        try{
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return 10;
      });
      //将futureTask传递一个线程运行
      new Thread(futureTask).start();
      System.out.println(System.currentTimeMillis());
      //futureTask.get()会阻塞当前线程，知道futureTask执行完成
      Integer result = futureTask.get();
      System.out.println(System.currentTimeMillis() + ":" + result);
  }
}
