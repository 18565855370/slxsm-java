package com.slxsm.threadpool.rethreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 除了第二个外其他的按照优先级排序，采用comparable<T>的compare to排序
 */
public class Demo3 {

    static class Task implements Runnable,Comparable<Task> {

        private String name;
        private int i;

        public Task(String name, int i){
            this.name = name;
            this.i = i;
        }

        @Override
        public int compareTo(Task o) {
            return Integer.compare(o.i,this.i);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "处理" + this.name);
        }
    }
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(1,1,60L, TimeUnit.SECONDS,new PriorityBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            String taskName = "任务" + i;
            executor.execute(new Task(taskName,i));
        }
        for (int i = 100; i >= 90; i--) {
            String taskName = "任务" + i;
            executor.execute(new Task(taskName,i));
        }
        executor.shutdown();
    }
}
