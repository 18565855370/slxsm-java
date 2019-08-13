package com.slxsm.threadpool.executors;

import java.util.concurrent.*;

/**
 * 使用ExecutorCompletionService,是一个执行线程的任务，构造函数传入Executor执行器，线程由 线程池提供执行，
 * ExecutorCompletionService.submit()提交，执行完成的任务会放在CompletionQueue（内部默认是LinkedBlockingQueue）阻塞队列，
 * 可以通过take()或poll()来获取已完成的任务，take()方法是阻塞的，就是说队列中没有就阻塞，等待下一个已完成的任务，poll方法不是阻塞的，
 * 当没有已完成的任务就直接返回null，poll(timeout,TimeUnit)是指定时间内获取已完成任务，获取到直接返回，获取不到并且超时的话，返回null
 */
public class Demo7 {

    static class GoodsModel {
        //商品名称
        String name;
        //开始购买时间
        long startTime;
        //购买结束时间
        long endTime;

        public GoodsModel(String name, long startTime, long endTime){
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString(){
            return "GoodsModel[ name=" + name + "下单时间：[" + startTime +"-" + endTime + "]，耗时：" +(endTime - startTime) +  "]";
        }
    }

    /**
     * 搬货上楼操作
     * @param model 商品信息
     */
    public static void moveUp(GoodsModel model){
        try{
            TimeUnit.SECONDS.sleep(5);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("将商品搬上楼，商品信息：" + model);
    }

    /**
     * 模拟下单
     * @param name 商品名称
     * @param costTime 购买时间成本
     * @return GoodsModel
     */
    private static Callable<GoodsModel> buyGoods(String name, long costTime){
        return () ->{
          long startTime = System.currentTimeMillis();
            System.out.println(startTime + "购买" + name + "下单");
            try{
                TimeUnit.SECONDS.sleep(costTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + name + "送到啦！");
            return new GoodsModel(name,startTime,endTime);
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime + "开始购物");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        ExecutorCompletionService<GoodsModel> service = new ExecutorCompletionService(executor);
        //异步下单冰箱
        service.submit(buyGoods("冰箱",3));
        //异步下单洗衣机
        service.submit(buyGoods("洗衣机",2));
        executor.shutdown();
        //商品数量为2
        int goodsNum = 2;
        for (int i = 0; i < goodsNum; i++) {
            GoodsModel goodsModel = service.take().get();
            moveUp(goodsModel);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime + "货已经到家啦！哈哈哈哈");
        System.out.println("总耗时：" + (endTime - startTime));
    }
}
