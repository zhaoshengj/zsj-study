package com.zsj.javaversion.java8.ThreadsAndExecutors;

import com.zsj.javaversion.java8.basic.FunctionalInterface;

import java.util.concurrent.TimeUnit;

/**
 * @author zsj
 * @date 2019-04-19  15:33
 */
public class Thread1 {

    public static void main(String[] args) {
        String name = Thread.currentThread().getName();
        System.out.println(name);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程二："+Thread.currentThread().getName());
            }
        }).start();

        Runnable task = () ->{
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程二："+Thread.currentThread().getName());

        };
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("主线程结束--");
    }
}
