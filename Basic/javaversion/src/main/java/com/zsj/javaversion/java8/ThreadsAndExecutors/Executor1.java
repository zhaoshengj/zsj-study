package com.zsj.javaversion.java8.ThreadsAndExecutors;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author zsj
 * @date 2019-04-19  16:06
 */
public class Executor1 {
    static  ExecutorService executor = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws InterruptedException {

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        executor.shutdown();
        executor.awaitTermination(4, TimeUnit.SECONDS);
    }

}
