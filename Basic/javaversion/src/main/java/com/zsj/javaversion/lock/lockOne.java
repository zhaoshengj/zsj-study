package com.zsj.javaversion.lock;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zsj
 * @date 2019-04-25  14:02
 *
 *   公平锁  /   非公平锁
 *
 */
public class lockOne {
    AtomicInteger sign = new AtomicInteger(0);

    int i = 0;

    private ReentrantLock lock = new ReentrantLock(true);

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    //非公平锁
    public  synchronized void test(int i) throws InterruptedException {
       // Thread.sleep(1000);
        System.out.print(i+"  -  ");
    }

    public void  test1(AtomicInteger i) throws InterruptedException {
        try{
            lock.lock();
            System.out.println("lock  -  :" + i);
           // Thread.sleep(1000);
        }finally {
            lock.unlock();
        }

    }


    @Test
    public void fair() throws ExecutionException, InterruptedException {

        for(i = 0;i < 10; i++){

            FutureTask task = new FutureTask(()->{
                test(i);
                return Thread.currentThread().getName();
            });

            Thread thread = new Thread(task);
            thread.start();
            System.out.println("  线程："+task.get());
        }

        Thread.sleep(1000);
    }

    @Test
    public void nofair() throws Exception {
        for (int i = 0; i < 10; i++) {
            sign = new AtomicInteger(i);
            //System.out.println("1----: "+sign);
           new Thread(() -> {
               try {
                   System.out.println("1----: "+sign);
                   test1(sign);

                   System.out.println("线程：" + Thread.currentThread().getName());

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }).start();

        }

        Thread.sleep(20000);
    }

}
