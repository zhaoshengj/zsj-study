package com.zsj.javaversion.JUC;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    static CountDownLatch latch = new CountDownLatch(101);

    public static void main(String[] args) throws InterruptedException {
        new MorePeople().start();
        People people = new People();
        for(int i=0;i<100;i++){
            new Thread(people).start();
        }
    }
    static class MorePeople extends Thread{
        @Override
        public void run() {
            System.out.println("全部准备");
            try {
                latch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("准备结束");
        }
    }

    static class People implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            latch.countDown();
        }
    }

}

