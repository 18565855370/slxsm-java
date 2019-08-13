package com.slxsm.threadpool.executors;

import java.util.concurrent.*;

/**
 * 正常订单下单流程
 */
public class Demo6 {

    static class GoodsModel {
        //货物名称
        String name;
        //货物下单时间
        long startTime;
        //货物送到时间
        long endTime;

        public GoodsModel(String name, long startTime, long endTime){
            this.name = name;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return name + ",下单时间[" + this.startTime + "," + this.endTime + "],耗时：" + (endTime - startTime);
        }
    }

    /**
     * 将商品搬上楼去
     * @param model 商品信息
     */
    static void moveUp(GoodsModel model){
        //休眠五秒钟，将货物搬上楼~
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("将商品搬上楼，商品信息：" + model);
    }

    /**
     * 模拟下单动作
     * @param name
     * @param costTime
     * @return
     */
    static Callable<GoodsModel> buyGoods(String name,long costTime){
        return () ->{
          long startTime = System.currentTimeMillis();
            System.out.println(startTime + "购买" + name + "下单");
            //模拟送货时间
            try{
                TimeUnit.SECONDS.sleep(costTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime + name + "货送到了");
            return new GoodsModel(name,startTime,endTime);
        };
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        System.out.println(startTime + "开始购物");
        //创建一个线程池，用来异步执行下单任务
        ExecutorService executor = Executors.newFixedThreadPool(5);
        //异步下单购买冰箱
        Future<GoodsModel> bxFuture = executor.submit(buyGoods("冰箱",5));
        //异步下单购买洗衣机
        Future<GoodsModel> xyjFuture = executor.submit(buyGoods("洗衣机",3));
        //关闭线程池
        executor.shutdown();
        //等待洗衣机送到
        GoodsModel xyjGoodsModel = xyjFuture.get();
        //将洗衣机送上楼
        moveUp(xyjGoodsModel);
        //等待冰箱送到
        GoodsModel bxGoodsModel = bxFuture.get();
        //将冰箱搬上楼
        moveUp(bxGoodsModel);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime + "货物已到家，哈哈哈");
        System.out.println("总耗时:" + (endTime - startTime));
    }
}
