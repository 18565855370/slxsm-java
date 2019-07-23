package com.slxsm.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 自定义一个线程池
 */
public class ConsumerThreadPoolExecutors {

    /**
     * 仓库，也就是阻塞队列，任务存放处
     */
    private BlockingQueue<Runnable> blockingQueue;

    /**
     * 线程集合
     */
    private List<Thread> workers;

    /**
     * 创建一个工作线程，用于获取队列中的任务并执行
     */
    public static class Worker extends Thread{

        private ConsumerThreadPoolExecutors pool;

        public Worker(ConsumerThreadPoolExecutors pool){
            this.pool = pool;
        }

        public void run() {
            while(pool.blockingQueue.size() > 0 || this.pool.isWorking){
                Runnable task = null;
                try {
                    if (this.pool.isWorking){
                        task = this.pool.blockingQueue.take();
                    }else {
                        task = this.pool.blockingQueue.poll();
                    }
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (task != null){
                    task.run();
                    System.out.println("线程：" + Thread.currentThread().getName() + "执行完毕");
                }
            }

        }
    }

    /**
     * 线程池初始化
     * @param coreSize 核心线程数
     * @param taskSize 工作线程数
     */
    public ConsumerThreadPoolExecutors(int coreSize, int taskSize){
        if (coreSize <= 0 || taskSize <= 0){
            throw new IllegalArgumentException("非法参数");
        }
        this.blockingQueue = new LinkedBlockingDeque<Runnable>(taskSize);
        this.workers = Collections.synchronizedList(new ArrayList<Thread>());
        for (int i = 0; i < coreSize; i++){
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    /**
     * 提交任务到队列中，如果isworking 为true，则继续提交任务，否则不在接收任务
     * @param task 任务
     */
    public boolean submit(Runnable task) throws InterruptedException {
        if (isWorking){
            return this.blockingQueue.offer(task);
        }else {
            return false;
        }

    }

    /**
     * 关闭线程池，1：仓库停止接收任务，2：仓库中剩余的任务要执行完，3：去仓库中获取任务不应该阻塞，4：一旦有线程被阻塞，则中断线程
     */
    //关闭线程池的标识
    public volatile boolean isWorking = true;

    /**
     * 关闭
     */
    public void shutdown(){
        this.isWorking = false;
        for (Thread thread : workers){
            if (Thread.State.BLOCKED.equals(thread.getState())){
                thread.interrupt();
            }
        }
    }
}
