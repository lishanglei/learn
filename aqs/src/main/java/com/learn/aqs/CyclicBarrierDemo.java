package com.learn.aqs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏-等待至barrier状态再全部同时执行
 * <p>
 * 通过CyclicBarrier可以实现让一组线程等待至某个状态之后再全部同时执行.
 * 叫做回环栅栏是因为当所有等待线程都被释放以后,CyclicBarrier可以被宠用.
 * 我们暂且把这个状态较作barrier,当调用await方法后,线程就处于barrier状态了
 *
 * public CyclicBarrier(int parties)
 * public CyclicBarrier(int parties, Runnable barrierAction)
 *
 * parties 是参与线程的个数
 * 第二个构造方法有一个 Runnable 参数，这个参数的意思是最后一个到达线程要做的任务
 * <p>
 * CyclicBarrier 中最重要的方法就是 await 方法，它有 2 个重载版本：
 * 1. public int await()：用来挂起当前线程，直至所有线程都到达 barrier 状态再同时执行后续任
 * 务；
 * 2. public int await(long timeout, TimeUnit unit)：让这些线程等待至一定的时间，如果还有
 * 线程没有到达 barrier 状态就直接让到达 barrier 的线程执行后续任务。
 *
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/6/21 14:28
 */
@Slf4j
public class CyclicBarrierDemo {

    private static int sleepTime = 1000;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                log.info("所有线程写入完毕，继续处理其他任务");
            }
        });
        Thread[] threads = new Thread[15];
        for (int i = 0; i < 15; i++) {
            threads[i] = new Thread() {
                @SneakyThrows
                @Override
                public void run() {
                    sleepTime++;
                    int nextInt =(int)(Math.random()*20000);
                    Thread.sleep(nextInt);
                    log.info("线程 {} 写入数据完 毕，等待其他线程写入完毕", Thread.currentThread().getName());
                    barrier.await();
                }
            };
        }

        for (Thread thread : threads) {
            thread.start();
        }


    }
}
