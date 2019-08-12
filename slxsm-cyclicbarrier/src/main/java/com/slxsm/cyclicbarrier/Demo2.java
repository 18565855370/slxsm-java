package com.slxsm.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier是可重复的，就是定义了一个cyclicBarrier后可以重复使用，countDownLatch是不能被重复使用多次的
 */
public class Demo2 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static class T extends Thread {
        private int sleep;

        public T(String name, int sleep){
            super(name);
            this.sleep = sleep;
        }

        @Override
        public void run() {
            //等待所有人到齐，先到的人坐着等着，什么事情都不要干
            this.eat();
            //等待所有人到齐后去下一个景点，先到的人到哪里等着，什么也不要干
            this.drive();
            //都是做完了自己的事情，到所有线程完事了到达await后继续
        }

        void eat(){
            try{
                TimeUnit.SECONDS.sleep(sleep);
                long startTime = System.currentTimeMillis();
                //调用await方法时，线程会被阻塞，直到所有线程都执行了await后所有线程才能继续执行
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + ",sleep: " + this.sleep + "，等待了：" + (endTime - startTime) + "ms, 开始吃饭了哈！");
                //模拟吃饭时间，毕竟吃饭也要时间嘛
                TimeUnit.SECONDS.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
        void drive(){
            try{
                long startTime = System.currentTimeMillis();
                //调用了await方法后，当前线程会被阻塞了，需要所有线程都到达了await后，所有此案城继续往下执行
                cyclicBarrier.await();
                long endTime = System.currentTimeMillis();
                System.out.println(this.getName() + ",sleep: " + this.sleep + "，等待了：" + (endTime - startTime) + "ms, 去下一个景点的路上！！！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new T("员工" + i,i).start();
        }
    }
}
