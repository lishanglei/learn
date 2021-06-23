package com.learn.aqs;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description : 自己实现一把锁
 * @date 2021/5/31 14:29
 */
public class LockCopy implements Lock {

    volatile AtomicReference<Thread> owner = new AtomicReference<>();
    volatile LinkedBlockingQueue<Thread> waters =new LinkedBlockingQueue<>();
    @Override
    public void lock() {

        while(!tryLock()){
            //没拿到锁,进入等待队列
            Thread thread = Thread.currentThread();
            waters.offer(thread);
            LockSupport.park(thread);
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {

        return owner.compareAndSet(null, Thread.currentThread());
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
