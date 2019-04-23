package com.zsj.javaversion.java8.ThreadsAndExecutors;

import java.util.concurrent.*;

/**
 * @author zsj
 * @date 2019-04-19  16:33
 */
public class CallableAndFuture {

    public static void main(String[] args) throws Exception{
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        FutureTask futureTask = new FutureTask(task);
        new Thread(futureTask,"1212-222").start();
        Object v = futureTask.get();
        System.out.println(v);

        FutureTask task1 = new FutureTask<>(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName());
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        });
       // System.out.println(task1.get()+"dsds");
        new Thread(task1).start();
        System.out.println(task1.get()+"ds");



        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);

        executor.shutdown();



    }
}
