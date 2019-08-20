package com.slxsm.ratelimit;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 露桶算法，就像水轮头一样，请求进入桶中，桶以一定量的速度流出，一旦进入桶的速度大于出桶的速度，则则会溢出，程序中会拒绝
 *
 * 举例子：比如北京开个大会议，地铁站就要限流，每次放入10个人，当安检过去几个人就再放入一定的人，哈哈，等的好痛苦
 */
public class Demo2 {

    static class BocketLimit {

        AtomicInteger integer = new AtomicInteger(1);
        //容量
        private int capcity;
        //流速
        private int flowRate;
        //流速时间单位
        private TimeUnit flowRateUnit;
        //漏铜流出的任务时间间隔
        private long flowRateNanosTime;

        private BlockingQueue<Node> queue;

        public BocketLimit(int capcity, int flowRate, TimeUnit flowRateUnit){
            this.capcity = capcity;
            this.flowRate = flowRate;
            this.flowRateUnit = flowRateUnit;
            this.bucketThreadWork();
        }

        //漏桶线程
        public void bucketThreadWork(){
            this.queue = new ArrayBlockingQueue<Node>(capcity);
            //漏桶流出的任务时间间隔（纳秒）
            this.flowRateNanosTime = flowRateUnit.toNanos(1) / flowRate;
            Thread thread = new Thread(this::bucketWork);
            thread.setName("漏桶线程-" + integer.getAndIncrement());
            thread.start();
        }

        //漏桶开始工作
        public void bucketWork(){
            while (true){
                Node node = this.queue.poll();
                if ( Objects.nonNull(node)){
                    //唤醒任务线程
                    LockSupport.unpark(node.thread);
                }
                //休眠flowRateNanosTime
                LockSupport.parkNanos(this.flowRateNanosTime);
            }
        }

        //返回一个楼桶
        public static BocketLimit build(int capcity, int flowRate, TimeUnit flowRateUnit){
            if (capcity < 0 || flowRate < 0){
                throw new IllegalArgumentException("capcity、flowRate必须大于0");
            }
            return new BocketLimit(capcity,flowRate,flowRateUnit);
        }

        //当前线程加入漏桶，返回false，表示漏桶已满；true：表示被漏桶限流成功，可以继续处理任务
        public boolean acquire(){
            Thread thread = Thread.currentThread();
            Node node = new Node(thread);
            if (this.queue.offer(node)){
                LockSupport.park();
                return true;
            }
            return false;
        }

        class Node {
            private Thread thread;

            public Node(Thread thread){
                this.thread = thread;
            }
        }
    }

  public static void main(String[] args) {
    BocketLimit bocketLimit = BocketLimit.build(10,60,TimeUnit.MINUTES);
    for (int i = 0; i < 15; i++) {
      new Thread(() -> {
          boolean acqire = bocketLimit.acquire();
          System.out.println(Thread.currentThread() + " " + acqire);
          try{
              TimeUnit.SECONDS.sleep(1);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
      }).start();
    }
  }
}
