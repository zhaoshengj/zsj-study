package com.zsj.javaversion.java8.ThreadsAndExecutors;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsj
 * @date 2019-04-19  18:15
 */
public class Volatile1 {

   public volatile int inc = 0;

    /*public  void increase() {
        inc++;
    }*/

    //方式一
    public synchronized void increase() {
        inc++;
    }
   //方式二
   /* Lock lock = new ReentrantLock();
    public void increase(){
        lock.lock();
        try {
            inc++;
        }finally {
            lock.unlock();
        }
    }*/
 /*  public  AtomicInteger inc = new AtomicInteger();

    public  void increase() {
        inc.getAndIncrement();
    }*/


    @Test
    public void test() throws InterruptedException {
        final Volatile1 test = new Volatile1();
        for(int i=0;i<1000;i++){
            new Thread(() ->{
                //System.out.println(Thread.currentThread().getName());
                for(int j=0;j<1000;j++)
                    test.increase();
            }).start();
        }

        while(Thread.activeCount()>1)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }


}
