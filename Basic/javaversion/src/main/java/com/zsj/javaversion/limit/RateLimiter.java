package com.zsj.javaversion.limit;

/**
 * @author zsj
 * @date 2019-05-16  09:46
 *
 * 限流抽象类
 */
public abstract class RateLimiter {

    protected int  rate = 1;

    /**
     * 是否通过请求（非阻塞）
     */
    public abstract boolean tryAcquire();

    /**
     * 是否允许通过(阻塞)
     */
    public void acquire(){
        do {
            if (tryAcquire()){
                return;
            }
            try {
                Thread.sleep(100);
                System.out.println("处理失败！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (true);
    }

}
