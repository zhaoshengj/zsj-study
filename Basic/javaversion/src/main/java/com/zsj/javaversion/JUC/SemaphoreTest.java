package com.zsj.javaversion.JUC;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    static Semaphore semaphore = new Semaphore(10,false);

    public static void main(String[] args) {
        Sys sys = new Sys();
        for(int i = 0;i<50;i++){
            new Thread(sys).start();
        }

    }

    static class Sys implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("处理"+Thread.currentThread().getName());
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
