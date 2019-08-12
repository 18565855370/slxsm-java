package com.slxsm.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier 如果在到达屏障打开前，有线程终端Interrupt了，则其他线程不再阻塞，开始执行
 */
public class Demo4 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        private int sleep;

        public T(String name, int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            long startTime = 0, endTime = 0;
            try{
                TimeUnit.SECONDS.sleep(sleep);
                startTime = System.currentTimeMillis();
                //调用await方法是，线程会被阻塞，知道所有线程都到达后才继续执行
                System.out.println(this.getName() + "到了！");
                cyclicBarrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            endTime = System.currentTimeMillis();
            System.out.println(this.getName() + ",睡眠了：" + sleep + "，等待了：" + (endTime - startTime) + "ms,开始吃饭了！！！");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int sleep = 0;
            if (i == 10){
                sleep = 10;
            }
            T t = new T("员工" + i,sleep);
            t.start();
            if (i == 5){
                //模拟员工55接了一个电话，将自己等待吃饭的状态打断
                TimeUnit.SECONDS.sleep(1);
                t.interrupt();
                TimeUnit.SECONDS.sleep(2);
            }
        }

    }
}
