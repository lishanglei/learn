package com.learn.xyz.thread_comm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程通信
 */
@Slf4j
public class Demo {

    private Object data;

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        //demo.waitNotifyTest();
        demo.parkTest();
    }

    public void waitNotifyTest() throws Exception {

        Thread consumer = new Thread(() -> {
            while (data == null) {
                log.info("此时没有数据,进入等待状态");
                synchronized (this) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            log.info("获取到数据");
        });
        consumer.start();
        log.info("睡眠三秒,模仿生产数据");
        Thread.sleep(3000);
        data = new Object();
        synchronized (this) {
            this.notifyAll();
            log.info("有数据了,通知消费者");
        }
    }

    public void parkTest() throws InterruptedException {

        Thread consumer = new Thread(() -> {
            while (data == null) {
                log.info("此时没有数据,进入等待状态");
                LockSupport.park(Thread.currentThread());
            }
            log.info("获取到数据");
        });
        consumer.start();
        log.info("睡眠三秒,模仿生产数据");
        Thread.sleep(3000);
        data = new Object();
        LockSupport.unpark(consumer);
        log.info("有数据了,通知消费者");
    }
}
