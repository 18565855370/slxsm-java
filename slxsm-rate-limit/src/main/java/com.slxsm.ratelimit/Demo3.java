package com.slxsm.ratelimit;

import com.google.common.util.concurrent.RateLimiter;

/**
 * 令牌桶算法，采用rateLimit
 */
public class Demo3 {
  public static void main(String[] args) {
      RateLimiter rateLimiter = RateLimiter.create(5);//设置QPS为5
    for (int i = 0; i < 10; i++) {
        rateLimiter.acquire();
        System.out.println(System.currentTimeMillis());
    }
    System.out.println("=============");
      //可以随时调整速率，我们将qps调整为10
      rateLimiter.setRate(10);
      for (int i = 0; i < 10; i++) {
          rateLimiter.acquire();
          System.out.println(System.currentTimeMillis());
      }
  }



}
