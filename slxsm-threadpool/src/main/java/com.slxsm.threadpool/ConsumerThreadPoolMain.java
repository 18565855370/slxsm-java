package com.slxsm.threadpool;

/**
 * 线程池的调用main类
 */
public class ConsumerThreadPoolMain {

    public static void main(String[] args) throws InterruptedException {
        ConsumerThreadPoolExecutors executors = new ConsumerThreadPoolExecutors(3,6);
        for (int i = 0; i < 6; i++){
            executors.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("提交一个线程");
                }
            });
        }
        executors.shutdown();
    }
}
