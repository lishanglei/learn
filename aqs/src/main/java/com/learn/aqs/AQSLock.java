package com.learn.aqs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :aqs同步器
 * @date 2021/5/25 16:28
 */
@Data
public class AQSLock {

    //Unsafe魔法类   可以绕过虚拟机,直接操作底层的内存
    /**
     * 当前加锁状态,记录加锁的次数
     */
    private volatile int state = 0;

    private volatile int valueOffset;

    /**
     * 当前持有锁的线程
     */
    private Thread lockHolder;

    /**
     * FIFO
     * 存放未获取到锁的队列
     * 基于cas的线程安全的队列
     */
    private ConcurrentLinkedQueue<Thread> waters = new ConcurrentLinkedQueue<>();


    @Autowired
    private Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();


    public void lock() {

        if (aquire()) {
            return;
        }
        Thread current = Thread.currentThread();
        waters.add(current);
        while (true) {

            /**
             * peek() 当没有数据时返回null,当有数据时则将队列头数据返回,但不移除队列头数据
             * poll() 当没有数据时返回null,当有数据时则将队列头数据返回,并移除队列头数据
             */
            if (current == waters.peek() && aquire()) {
                //将头部的线程从队列中移除
                waters.poll();
                return;
            }
            //将传入的线程阻塞
            LockSupport.park(current);
        }

    }

    public void unlock() {

        Thread current = Thread.currentThread();
        if (current != lockHolder) {
            throw new RuntimeException("lockHolder is not current thread");
        }
        int state = getState();
        if (compareAndSwapState(state, 0)) {
            setLockHolder(null);
            //从队列中获取一个阻塞的线程
            Thread first = waters.peek();
            if (first != null) {
                LockSupport.unpark(first);
            }
        }
    }

    /**
     * 原子操作
     *
     * @param expect 预期值
     * @param update 修改值
     * @return
     */
    public final boolean compareAndSwapState(int expect, int update) {

        /**
         * 此方法是Java的native方法，并不由Java语言实现。
         *
         * 方法的作用是，读取传入对象o在内存中偏移量为offset位置的值与期望值expected作比较。
         *
         * 相等就把x值赋值给offset位置的值。方法返回true。
         *
         * 不相等，就取消赋值，方法返回false。
         *
         * 这也是CAS的思想，及比较并交换。用于保证并发时的无锁并发的安全性。
         */
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    /**
     * 尝试获取锁
     *
     * @return
     */
    public boolean aquire() {

        //cas比较与交换 原子算法
        Thread current = Thread.currentThread();
        //初始状态
        int c = getState();
        if (c == 0) {//同步器还没有被持有
            if ((waters.size() == 0 || current == waters.peek()) && compareAndSwapState(0, 1)) {
                setLockHolder(current);
                return true;
            }
        }
        return false;
    }


}
