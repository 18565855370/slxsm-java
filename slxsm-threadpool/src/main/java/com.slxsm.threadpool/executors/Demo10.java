package com.slxsm.threadpool.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * demo9采用的自定义的invokeAny，demo10用的是ExecutorService自带的invokeAny，效果一样
 *
 * invokeAll是执行完所有的在返回List<Future<T>
 */
public class Demo10 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i >0 ; i--) {
            int j = i * 2;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(j);
                return j;
            });
        }
        //List<Future<Integer>> integer = executor.invokeAll(list);
        Integer integer = executor.invokeAny(list);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + "结果：" + integer);
        executor.shutdown();
    }
}
