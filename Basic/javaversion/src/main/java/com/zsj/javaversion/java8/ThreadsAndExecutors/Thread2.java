package com.zsj.javaversion.java8.ThreadsAndExecutors;

import java.util.concurrent.CountDownLatch;

/**
 * @author zsj
 * @date 2019-04-19  15:52
 */
public class Thread2 {

    private static final CountDownLatch task = new CountDownLatch(2);

    public static void main(String[] args) {
        ThreadLocal loacl = new ThreadLocal();
        loacl.set(1);
        for(int i = 0 ;i < 2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程"+loacl.get());
                    task.countDown();
                }
            }).start();
        }


    }
}
