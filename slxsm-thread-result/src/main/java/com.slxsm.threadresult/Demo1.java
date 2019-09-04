package com.slxsm.threadresult;

import java.util.concurrent.TimeUnit;

/**
 * 线程返回1
 * join阻塞主线程,只能阻塞一个线程
 */
public class Demo1 {

    /**
     * 静态内部类
     * @param <T>
     */
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
    //用于存放子线程执行的结果
    Result<Integer> result = new Result<Integer>();
    //创建一个子线程
      Thread t = new Thread(() -> {
        try{
            TimeUnit.SECONDS.sleep(3);
            result.setResult(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
      });
      t.start();
      //让主线程等待thread线程执行完毕之后再继续，join方法会让当前线程阻塞
      t.join();
      //获取thread线程的执行结果
      Integer rs = result.getResult();
      System.out.println(System.currentTimeMillis());
      System.out.println(System.currentTimeMillis() + ":" + rs);
  }
}
