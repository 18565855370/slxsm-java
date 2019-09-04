package com.slxsm.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo3 {

  public static void main(String[] args) throws InterruptedException {
      test1();
  }

  private static void test1() throws InterruptedException {
      int threadSize = 50;
      CountDownLatch cdl = new CountDownLatch(threadSize);
      for (int i = 0; i < threadSize; i++){
      new Thread(
              () -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(2);
                  System.out.println("111");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
              })
          .start();
      }
      cdl.await();
    System.out.println("1111111111111");
  }
}
