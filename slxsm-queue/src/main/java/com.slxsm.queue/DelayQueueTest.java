package com.slxsm.queue;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Delay是一个延时队列，内部采用的是priorityQueue优先级队列实现，这个队列表示任务可延时，不到时间不会被消费
 */
public class DelayQueueTest {

    static class Msg implements Delayed{
        //优先级，越小优先级越高
        private int priority;
        //消息
        private String msg;
        //定时发送消息，毫秒格式
        private long sendTimes;

        public Msg(int priority, String msg, long sendTimes){
            this.priority = priority;
            this.msg = msg;
            this.sendTimes = sendTimes;
        }

        @Override
        public String toString() {
            return "Msg{" +
                    "priority=" + priority +
                    ", msg='" + msg + '\'' +
                    ", sendTimes=" + sendTimes +
                    '}';
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.sendTimes - Calendar.getInstance().getTimeInMillis(),TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (o instanceof Msg){
                Msg o2 = (Msg) o;
                return Integer.compare(this.priority,o2.priority);
            }
            return 0;
        }
    }

    static DelayQueue<Msg> queue = new DelayQueue<>();

    static {
        new Thread(() ->{
            while (true){
                Msg msg;
                try{
                    //获取一条推送消息，此方法会进行阻塞，直到返回结果
                    msg = queue.take();
                    long endTime = System.currentTimeMillis();
                    System.out.println(String.format("定时发送时间：%s,实际发送时间：%s,发送消息:%s", msg.sendTimes, endTime, msg));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //推送消息，需要发送推送消息的调用该方法，会将推送信息先加入推送队列
    public static void pushMsg(int priority, String msg, long sendTimeMs){
        queue.put(new Msg(priority,msg,sendTimeMs));
    }

  public static void main(String[] args) {
    for (int i = 5; i >= 1; i--) {
        String msg = "大家一起来耍，第" + i + "天";
        pushMsg(i,msg,Calendar.getInstance().getTimeInMillis() + i * 2000);
    }
  }
}
