package com.learn.zk.pack2;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description : 分布式锁
 * @date 2021/7/21 9:33
 */
public class DistributeLockTest {

    public static void main(String[] args) {

        final DistributeLock lock1 = new DistributeLock();
        final DistributeLock lock2 = new DistributeLock();
        lock1.distributedLock();
        lock2.distributedLock();

        new Thread(() -> {

            lock1.zkLock();
            System.out.println("线程1 启动  获取到锁");
            try {
                Thread.sleep(5 * 1000);
                lock1.zkUnlock();
                System.out.println("线程1 释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {

            lock2.zkLock();
            System.out.println("线程2 启动  获取到锁");
            try {
                Thread.sleep(5 * 1000);
                lock2.zkUnlock();
                System.out.println("线程2 释放锁");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
    }

}
