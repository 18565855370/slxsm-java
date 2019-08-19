package com.slxsm.queue;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 无界阻塞队列，内部是数组组成，容量不够可扩容，但是也要防止oom，按照优先级进行存储消费，内部实现的是创建比较器Comparator或实现Comparable接口
 * 业务倾向为有先后顺序的数据，如公告等
 */
public class PriorityBlockingQueueTest {

    static class Msg implements Comparable<Msg>{

        //优先级
        private int priority;
        //消息
        private String msg;

        public Msg(int priority, String msg){
            this.priority = priority;
            this.msg = msg;
        }

        //(x < y) ? -1 : ((x == y) ? 0 : 1)
        @Override
        public int compareTo(Msg o) {
            return Integer.compare(this.priority,o.priority);
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    static PriorityBlockingQueue<Msg> queue = new PriorityBlockingQueue<>();

    static {
        new Thread(() -> {
            while (true){
                Msg msg;
                try{
                    long startTime = System.currentTimeMillis();
                    msg = queue.take();
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("[%s,%s,take耗时:%s],%s,发送消息:%s", startTime, endTime, (endTime - startTime), Thread.currentThread().getName(), msg));
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void pushMsg(Msg msg){
        queue.put(msg);
    }

  public static void main(String[] args) {
    for (int i = 5; i>=1; i--) {
        String msg = "一起来玩，第" + i + "天";
        pushMsg(new Msg(i,msg));
    }
  }
}
