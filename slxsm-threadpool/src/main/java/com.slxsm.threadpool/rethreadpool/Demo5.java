package com.slxsm.threadpool.rethreadpool;

import java.util.concurrent.*;

/**
 * 自定义reject策略
 */
public class Demo5 {

    static class Task implements Runnable {
        String name;
        public Task(String name){
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
        }

        @Override
        public String toString() {
            return "Task{name=" + name + "}";
        }
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                1,
                60L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                Executors.defaultThreadFactory(),
                (r, executors) -> System.out.println("无法处理此任务：" + r.toString()));
        for (int i = 0; i < 5; i++) {
            executor.execute(new Task("任务-" + i));
        }
        executor.shutdown();
    }
}
