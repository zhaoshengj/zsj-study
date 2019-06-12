package com.zsj.javaversion.limit;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zsj
 * @date 2019-05-16  09:33
 */
public class CounterLimiter extends RateLimiter{

    //计数器
    private volatile int counter = 0;
    //上次计数时间
    private volatile long lastTime = 0;

    //cas 自旋锁
    private Lock lock = new ReentrantLock();


    //计数器
    @Override
    public boolean tryAcquire(){
        lock.lock();
        try {
            //判断是否过期
            if(System.currentTimeMillis() - lastTime > 1000){
                counter = 0;
                lastTime = System.currentTimeMillis();
            }
            //计数
            counter++;

            //是否超过速度
            if(counter <= rate){
                return true;
            }

        }finally {
            lock.unlock();
        }

        return false;

    }

    @Test
    public  void main() {
        for(int i = 0;i<10;i++){
            acquire();
            System.out.println(counter+"---"+lastTime+"--"+rate);
        }
    }

}
