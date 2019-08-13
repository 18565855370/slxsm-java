package com.slxsm.threadpool.rethreadpool;

import org.omg.SendingContext.RunTime;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池扩展
 */
public class Demo6 {

    static class Task implements Runnable {
        String name;

        public Task(String name){
            this.name = name;
            System.out.println(Runtime.getRuntime().availableProcessors());
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name=" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                10,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                (r,executors) -> {
                    //自定义饱和策略
                    System.out.println(Thread.currentThread().getName() + "无法处理该线程" + r.toString());
                }){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(System.currentTimeMillis() + "," + t.getName() + "，开始处理任务" + r.toString());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (t != null){
                    System.out.println(t.getMessage());
                }
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",任务：" + r.toString() + ",执行完毕！");
            }

            @Override
            public void terminated() {
                System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",关闭线程池!");
            }
        };
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task("任务-" + i));
        }
        executor.shutdown();
    }
}
