package com.slxsm.threadpool.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * CompletionService有取消任务的能力,执行完成第一个返回的时候cancel
 */
public class Demo9 {

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = 0; i < taskCount; i++) {
            int j = i * 2;
            list.add(() -> {
                TimeUnit.SECONDS.sleep(3);
                return j;
            });
        }
        Integer integer = invokeAny(executor,list);
        System.out.println("耗时：" + (System.currentTimeMillis() - startTime) + ",执行结果：" + integer);
        executor.shutdown();
    }

    public static <T> T invokeAny(Executor e, Collection<Callable<T>> solvers) throws InterruptedException {
        CompletionService<T> service = new ExecutorCompletionService<>(e);
        List<Future<T>> futures = new ArrayList<>();
        for (Callable<T> callable :  solvers) {
            futures.add(service.submit(callable));
        }
        int n = solvers.size();
        try{
            for (int i = 0; i < n; i++) {
                T t = service.take().get();
                if (t != null){
                    return t;
                }
            }
        }catch (InterruptedException e1){
            e1.printStackTrace();
        }catch (ExecutionException ex){
            ex.printStackTrace();
        }finally {
            for (Future<T> future : futures){
                future.cancel(true);
            }
        }
        return null;
    }
}
