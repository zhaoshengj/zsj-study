package com.zsj.javaversion.lock;

import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReenTrantLockLearn {


    static  ReentrantLock reentrantLock = new ReentrantLock();

    static  ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    public static void main(String[] args) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                testSync();
            }
        };

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                testSync();
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                testSync();
            }
        };


        thread.setName("thread");
        thread1.setName("thread1");
        thread2.setName("thread2");

        thread.start();
        LockSupport.unpark(thread);

        thread1.start();
        LockSupport.unpark(thread1);

        thread2.start();
        LockSupport.unpark(thread2);


    }

    public static void testSync(){

        LockSupport.park();

        reentrantLock.lock();

        try {
            reentrantLock.lockInterruptibly();


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());


    }
}
