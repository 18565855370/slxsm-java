package com.slxsm.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 等待超时
 */
public class Demo5 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        private int sleep;

        public T(String name, int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            long startTime = 0,endTime = 0;
            try {
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                System.out.println(this.getName() + " 到了");
                if (this.getName().equals("员工1")){
                    //当调用了await后，线程会被阻塞，当所有线程都到达await时继续往下执行，但是等待时间超时会除法TImeOutException异常，所有线程不再等待，全部执行
                    cyclicBarrier.await(5,TimeUnit.SECONDS);
                }else {
                    cyclicBarrier.await();
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName() + ",sleep:" + this.sleep + " 等待了" + (endTime - startTime) + "(ms),开始吃饭了！");
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("员工" + i,i).start();
        }
    }
}
