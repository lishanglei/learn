package com.learn.aqs;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :00 0
 *
 * @date 2021/5/31 11:22
 */
public class LockReadWriteDemo1Test {

    private static LockReadWriteDemo1Test lockDemo1Test = new LockReadWriteDemo1Test();
    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = LOCK.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = LOCK.writeLock();
    public static void main(String[] args) {

        new Thread(() -> {
            lockDemo1Test.read(Thread.currentThread());
        }).start();


        new Thread(() -> {
            lockDemo1Test.read(Thread.currentThread());
        }).start();


        new Thread(() -> {
            lockDemo1Test.write(Thread.currentThread());
        }).start();

    }

    public void read(Thread thread) {

        try {
            readLock.lock();
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + " 正在进行[读]操作");
            }
            System.out.println(thread.getName() +"读操作执行完毕");
        } finally {
            readLock.unlock();
        }
    }

    public void write(Thread thread) {


        try {
            writeLock.lock();
            long start = System.currentTimeMillis();

            while (System.currentTimeMillis() - start <= 1) {
                System.out.println(thread.getName() + " 正在进行[写]操作");
            }
            System.out.println(thread.getName() +"写操作执行完毕");
        } finally {
            writeLock.unlock();
        }
    }
}
