package com.learn.aqs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch类位于java.util.concurrent包下,利用它可以实现累次计数器的功能,
 * 比如有一个任务A,它要等待其它4个任务执行完毕之后才能执行
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/6/21 14:16
 */
@Slf4j
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch =new CountDownLatch(2);
        new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                log.info("子线程 {} 正在执行",Thread.currentThread().getName());
                Thread.sleep(3000);
                log.info("子线程 {} 执行完毕",Thread.currentThread().getName());
                latch.countDown();
            }
        }.start();

        new Thread(){
            @SneakyThrows
            @Override
            public void run() {
                log.info("子线程 {} 正在执行",Thread.currentThread().getName());
                Thread.sleep(3000);
                log.info("子线程 {} 执行完毕",Thread.currentThread().getName());
                latch.countDown();
            }
        }.start();
        log.info("等待两个子线程任务执行完毕.......");
        //await()方法会在信号量归零前阻塞主线程
        latch.await();
        log.info("2 个子线程已经执行完毕");
        log.info("继续执行主线程");
    }

}
