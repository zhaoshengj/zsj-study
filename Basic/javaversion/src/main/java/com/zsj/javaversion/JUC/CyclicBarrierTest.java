package com.zsj.javaversion.JUC;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(6,Thread.currentThread());

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        System.out.println("开始");
        Sys sys = new Sys();
        for(int i = 0;i<5;i++){
            new Thread(sys).start();
        }
        cyclicBarrier.await();
        System.out.println("结束");
    }

    static class Sys implements Runnable{

        @Override
        public void run() {
            System.out.println("开始"+Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("处理结束"+Thread.currentThread().getName());
        }
    }




}
