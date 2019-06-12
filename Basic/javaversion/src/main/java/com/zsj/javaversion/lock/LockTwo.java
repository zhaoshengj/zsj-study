package com.zsj.javaversion.lock;

import org.junit.Test;

/**
 * @author zsj
 * @date 2019-05-17  10:10
 *
 * 验证锁机制，
 */
public class LockTwo {
    int result;

    public int getResult() {
        return result;
    }

    public synchronized void setResult(int result) {
        this.result = result;
    }


    @Test
    public void main() {
        LockTwo threadSafeCache = new LockTwo();
        /**
         * 先set值没问题
         */
       // threadSafeCache.setResult(200);
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadSafeCache.setResult(200);
    }
}

class LockTwo1{
    volatile int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static void main(String[] args) {
        LockTwo1 threadSafeCache = new LockTwo1();
        for (int i = 0; i < 8; i++) {
            new Thread(() -> {
                int x = 0;
                while (threadSafeCache.getResult() < 100) {
                    x++;
                }
                System.out.println(x);
            }).start();
        }

        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        threadSafeCache.setResult(200);
    }
}


