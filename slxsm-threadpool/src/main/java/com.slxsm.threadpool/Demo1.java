package com.slxsm.threadpool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 调用多个接口，接口间没有依赖，所以可以多线程异步调用，充分优化
 */
public class Demo1 {

    /**
     * 获取商品基本信息
     * @param goodsId 商品id
     * @return 商品基本信息
     */
    public String goodsDetailModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200毫秒
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品ID" + goodsId + ",基本商品信息...";
    }

    /**
     *  获取商品图片信息
     * @param goodsId 商品Id
     * @return 商品图片列表
     */
    public List<String> goodsImgsModelList(long goodsId) throws InterruptedException {
        //模拟耗时，耗时200毫秒
        TimeUnit.MILLISECONDS.sleep(200);
        return Arrays.asList("图1","图2","图3");
    }

    /**
     * 获取商品描述信息
     * @param goodsId 商品Id
     * @return 商品描述信息
     * @throws InterruptedException 异常
     */
    public String goodsExtModel(long goodsId) throws InterruptedException {
        //模拟耗时，休眠200毫秒
        TimeUnit.MILLISECONDS.sleep(200);
        return "商品Id:" + goodsId + ",商品描述信息...";
    }

    //创建线程池
    ExecutorService service = Executors.newFixedThreadPool(10);

    public Map<String, Object> goodsDetail(long goodsId) throws ExecutionException, InterruptedException {
        Map<String, Object> result = new HashMap<>();

        //异步获取商品基本信息
        Future<String> detailFuture = service.submit(() -> goodsDetailModel(goodsId));
        //异步获取商品牌图片列表
        Future<List<String>> imgFuture = service.submit(() -> goodsImgsModelList(goodsId));
        //异步获取商品描述信息
        Future<String> extFuture = service.submit(() -> goodsExtModel(goodsId));

        result.put("gooldsDetailModel",detailFuture.get());
        result.put("goodsImgsModelList",imgFuture.get());
        result.put("goodsExtModel",extFuture.get());
        return result;
    }

  public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        Map<String, Object> map = new Demo1().goodsDetail(1L);
        System.out.println(map);
        System.out.println("耗时(ms)：" + (System.currentTimeMillis() - startTime));
  }
}
