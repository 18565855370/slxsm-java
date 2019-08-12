package com.slxsm.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier可以自定义barrierAction操作，可选，设置了这个属性后，则在阻塞线程数达到设定值屏障打开前，
 * 会调用barrierAction的run()方法，完成用户自定义操作,只有最后一个线程来执行这个Action操作
 */
public class Demo3 {

    public static CyclicBarrier cyclicBarrier = new CyclicBarrier(10,() ->{
        //默认操作，耗时2秒，其他线程等待2秒
        try {
            TimeUnit.SECONDS.sleep(2);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "说，让大家久等了，给大家倒酒赔罪了！");
    });

    public static class T extends Thread {
        private int sleep;

        public T(String name, int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try{
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                //调用await方式时，当前线程会被阻塞，带所有线程都到达await上，才可以继续执行
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + ",sleep: " + sleep + ",耗时：" + (endTime - startTime) + " ms, 开始吃饭了！！！");
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new T("员工" + i,i).start();
        }
    }
}
