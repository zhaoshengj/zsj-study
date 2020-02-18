package study.thread;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.util.HashSet;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池源码分析 学习
 */
public class ThreadPoolExecutor {

    private final ReentrantLock mainLock = new ReentrantLock();

    // 线程池中重要的变量 ctl，类型为 AtomicInteger，一个变量同时记录线程池状态和线程个数
    // Integer 的位数为 32 位，其中高 3 位表示线程池的状态，低 29 位表示线程的个数。默认为 RUNNING 状态，线程个数为 0
    private final AtomicInteger ctl = new AtomicInteger(/*ctlOf(RUNNING, 0)*/);

    // 核心线程数
    private volatile int corePoolSize;
    //最大线程数
    private volatile int maximumPoolSize;

    private final HashSet<Worker> workers = new HashSet<Worker>();

    //存活时间，当线程个数大于了核心线程数，且处于空闲状态，这些空闲线程可存活的最大时间
    private volatile long keepAliveTime;

    //拒绝策略，当线程个数达到最大线程数，同时任务队列满了。就会执行拒绝策略。
    // 拒绝策略有：AbortPolicy（直接抛出异常）、
    // CallerRunsPolicy（调用者所在线程来执行任务）、
    // DiscardOldestPolicy（从任务队列中移除一个待执行的任务（最早提交的），然后再次执行任务）、
    // DiscardPolicy（直接抛弃任务）
    private volatile RejectedExecutionHandler handler;

    //创建线程的工厂类
    private volatile ThreadFactory threadFactory;

    //存放还未被执行任务的阻塞队列
    private final BlockingQueue<Runnable> workQueue;
    private final AccessControlContext acc;


    private static final RejectedExecutionHandler defaultHandler =
            new java.util.concurrent.ThreadPoolExecutor.AbortPolicy();

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {

        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                Executors.defaultThreadFactory(), defaultHandler);
    }


    //配置线程池
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }

    //尝试把任务交给线程池执行，执行任务的线程可能是新建的，也可能是复用了线程池中的。
    // 如果线程池不能执行该任务（可能原因有两个，1.线程池已经关闭了，2.线程达到了最大容量）就会执行拒绝策略
   /* public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        // 获取表示线程池状态和线程个数的组合变量 ctl
        int c = ctl.get();
        // 判断线程个数是否小于核心线程数，如果小于就新建一个核心线程来执行任务
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        // 如果线程池处于 RUNNAING 状态，就把任务添加到阻塞队列中（代码运行到这里说明要么线程个数>=核心线程数，要么执行 addWorder 方法失败）
        if (isRunning(c) && workQueue.offer(command)) {
            // 再次获取组合变量 ctl，做二次检查（因为可能在此之前，线程池的状态已经发生了改变）
            int recheck = ctl.get();
            // 如果线程池状态不是 RNUUAING 状态，就把该任务从阻塞任务队列中移除，并执行拒绝策略
            if (! isRunning(recheck) && remove(command))
                reject(command);
                // 如果线程池中线程个数为 0，就新建一个线程
            else if (workerCountOf(recheck) == 0)
                addWorker(null, false);
        }
        // 如果阻塞任务队列满了，新建线程，如果创建线程失败（即线程个数达到了最大线程个数），执行拒绝策略
        else if (!addWorker(command, false))
            reject(command);
    }*/

    /**
     * 创建新的worker
     *
     * @param firstTask 提交给线程的任务,要最先执行，可以为 null
     * @param core  如果为 true，表示以核心线程数为界创建线程  为 false 表示以最大线程数为界创建线程
     * @return
     */
    private boolean addWorker(Runnable firstTask, boolean core) {
        retry:
     /*   for (;;) {
            // 获取 ctl
            int c = ctl.get();
            // 获取线程池的状态
            int rs = runStateOf(c);

            // 这里的判断条件有点多，拆成 rs>=SHUTDOWN 和 !(rs == SHUTDOWN && firstTask == null &&!workQueue.isEmpty())
            // !(rs == SHUTDOWN && firstTask == null &&!workQueue.isEmpty()) 逆着考虑 ,如下：
            // rs!=SHUTDOWN 也就是为大于 shutdown，为 stop,tidying,terminated
            // firstTask != null
            // workQueue.isEmpty()
            // 如果线程池处于关闭状态，且满足下面条件之一的，不创建 worker
            //      线程池处于 stop,tidying,terminated 状态
            //      firstTask != null
            //      workQueue.isEmpty()
            // 注意：如果线程池处于 shutdown，且 firstTask 为 null,同时队列不为空，允许创建 worker
            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN &&
                    ! (rs == SHUTDOWN &&
                            firstTask == null &&
                            ! workQueue.isEmpty()))
                return false;

            for (;;) {
                // 获取工作线程数
                int wc = workerCountOf(c);
                // 工作线程数大于最大容量或者工作线程数超过线程数的边界(根据 core 的值取不同的值) 时 不创建worker
                if (wc >= CAPACITY ||
                        wc >= (core ? corePoolSize : maximumPoolSize))
                    return false;
                // 工作线程数 +1  通过 CAS
                // 这里如果失败，表示有并发操作
                if (compareAndIncrementWorkerCount(c))
                    // 调出循环，执行真正的创建 worker 逻辑
                    break retry;
                // 因为存在并发，需要再读取 ctl 值进行状态判断
                // Re-read ctl
                c = ctl.get();
                // 如果线程状态发生了变化，回到外部循环
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        // 校验已经都通过，开始创建 worker
        // 是否已经启动了 worker
        boolean workerStarted = false;
        // 是否已经添加了 worker
        boolean workerAdded = false;
        Worker w = null;
        try {
            // 把 task 封装成 worker，通过线程工厂创建线程，最后会把任务设置到 Thread 的 target 属性上，后续在执行线程的 start 方法时，就会执行对应的任务的 run 方法
            w = new Worker(firstTask);
            // 获取 worker 中的线程
            final Thread t = w.thread;
            if (t != null) {
                // 获取对象锁
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    // 如果线程池处于 Running 状态 或者 线程池处于 shutdown 状态且任务为 null（执行任务队列中的任务）
                    if (rs < SHUTDOWN ||
                            (rs == SHUTDOWN && firstTask == null)) {
                        // precheck that t is startable
                        // 检查线程是否为启动状态，如果为启动状态抛异常
                        if (t.isAlive())
                            throw new IllegalThreadStateException();
                        // 把新建的 worker 添加到 worker 集中
                        workers.add(w);
                        int s = workers.size();
                        // largestPoolSize 记录 workers 中个数存在过的最大值
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                // 新建的 worker 添加成功就启动线程，后续有分析
                if (workerAdded) {
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            // 线程没有启动成功，对上面创建线程的过程做回滚操作
            if (! workerStarted)
                // 回滚操作，比如把 worker 从 workers 中移除，把线程数减一
                addWorkerFailed(w);
        }
        return workerStarted;*/
     return false;
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null)
                // 从 workers 中移除 worker
                workers.remove(w);
            // 把线程数减一
            //decrementWorkerCount();
            //tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }

    private final class Worker{
       /* Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            // 这里默认的线程工厂是 DefaultThreadFactory
            this.thread = getThreadFactory().newThread(this);
        }*/
    }
}
