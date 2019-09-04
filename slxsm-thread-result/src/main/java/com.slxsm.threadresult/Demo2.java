package com.slxsm.threadresult;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 线程返回2
 * CountDownLatch
 */
public class Demo2 {

    static class Result<T>{
        T result;

        public T getResult(){
            return result;
        }
        public void setResult(T result){
            this.result = result;
        }
    }

  public static void main(String[] args) throws InterruptedException {
    System.out.println(System.currentTimeMillis());
    CountDownLatch cdl = new CountDownLatch(2);
    //用于存放子线程执行的结果
    Result<Integer> result = new Result<>();
    Thread t = new Thread(() -> {
       try{
           TimeUnit.SECONDS.sleep(3);
           result.setResult(10);
       }catch (InterruptedException e){
           e.printStackTrace();
       }finally{
           cdl.countDown();
       }
    });
    t.start();
    //cdl await 会让当前线程阻塞，当cdl中的计数器变为0的时候，await会返回
    cdl.await();
    // 获取线程thread线程的执行结果
    Integer rs = result.getResult();
    System.out.println(System.currentTimeMillis());
    System.out.println(System.currentTimeMillis() + ":" + rs);
  }
}
