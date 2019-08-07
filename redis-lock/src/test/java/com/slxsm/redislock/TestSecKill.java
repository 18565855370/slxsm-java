package com.slxsm.redislock;

import com.slxsm.redislock.interceptor.CacheLockInterceptor;
import com.slxsm.redislock.service.SecKillInterface;
import com.slxsm.redislock.service.impl.SecKillImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Proxy;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSecKill {

    @Test
    public void testSecKill(){
        int threadCount = 1000;
        int splitPoint = 500;
        CountDownLatch endCount = new CountDownLatch(threadCount);
        CountDownLatch beginCount = new CountDownLatch(1);
        SecKillImpl testClass = new SecKillImpl();

        Thread[] threads = new Thread[threadCount];
        //起500个线程，秒杀一个商品
        for (int i = 0; i < splitPoint; i++) {
            threads[i] = new Thread(() -> {
               try{
                   beginCount.await();
                   SecKillInterface proxy = (SecKillInterface) Proxy.newProxyInstance(SecKillInterface.class.getClassLoader(),
                           new Class[]{SecKillInterface.class},new CacheLockInterceptor(testClass));
                   proxy.secKill("test",10000001L);
                   endCount.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            });
            threads[i].start();
        }

        long startTime = System.currentTimeMillis();
        //主线程释放开始信号量，并等待结束信号量，这样做保证1000个线程做到完全同时执行，保证测试的正确性
        beginCount.countDown();

        try {
            //主线程等待结束信号量
            endCount.await();
            //观察秒杀结果是否正确
            System.out.println(SecKillImpl.inventory.get("10000001L"));
            System.out.println(SecKillImpl.inventory.get("10000002L"));
            System.out.println("error count " + CacheLockInterceptor.ERROR_COUNT);
            System.out.println("total cost " + (System.nanoTime() - startTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
