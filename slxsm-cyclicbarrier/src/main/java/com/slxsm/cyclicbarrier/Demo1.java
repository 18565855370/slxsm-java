package com.slxsm.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier被称为循环屏障，cyclicBarrier的await被调用一次就剪一，知道指定的所有线程都调用了await后所有线程继续执行
 * 有个问题就是如果，我定义了15个线程，但是cyclicBarrier中计数器的值为10，这样的话那剩下的5个会阻塞，知道其他线程到来凑够10个才可以继续执行
 * countDownLatch与cyclicBarrier的重要区别就是cdl是主线程等待所有子线程countDown后立即返回，而cyb是所有线程自己等待，不存在主线程这么个在其中。
 */
public class Demo1 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        int sleep;
        public T(String name, int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                //调用await的时候，当前线程将会被阻塞，需要等待其他线程都到达await了才可以继续执行
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + ",sleep：" +this.sleep + ",等待了：" + (endTime - startTime) + "ms.开始吃饭了！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++){
            new T("员工" + i,i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("====================第二批==========================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++){
            new T("第二批员工" + i,i).start();
        }
    }
}
