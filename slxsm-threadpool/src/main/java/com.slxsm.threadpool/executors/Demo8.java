package com.slxsm.threadpool.executors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * 继Demo7 的 ExecutorCompletionService再来一次
 *
 * 执行一批任务，在获取结果
 */
public class Demo8 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Callable<Integer>> list = new ArrayList<>();
        int taskCount = 5;
        for (int i = taskCount; i > 0; i--) {
            int j = i * 2;
            list.add(() ->{
               TimeUnit.SECONDS.sleep(j);
                System.out.println("j: " + j);
               return j;
            });
        }
        solve(executor,list, a -> {
            System.out.println(System.currentTimeMillis() + ":" + a);
        });
        executor.shutdown();
    }

    public static <T> void solve(Executor e, Collection<Callable<T>> solvers, Consumer<T> use) throws InterruptedException, ExecutionException {
        CompletionService<T> cs = new ExecutorCompletionService<T>(e);
        for (Callable<T> s : solvers){
            cs.submit(s);
        }
        int n = solvers.size();
        for (int i = 0; i < n; i++) {
            T t = cs.take().get();
            if (t != null){
                use.accept(t);
            }
        }
    }
}
