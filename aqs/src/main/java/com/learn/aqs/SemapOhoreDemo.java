package com.learn.aqs;

import lombok.extern.slf4j.Slf4j;
import sun.plugin2.message.BestJREAvailableMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

/**
 * Semaphore 是一种基于计数的信号量。它可以设定一个阈值，基于此，多个线程竞争获取许可信
 * 号，做完自己的申请后归还，超过阈值后，线程申请许可信号将会被阻塞。Semaphore 可以用来
 * 构建一些对象池，资源池之类的，比如数据库连接池
 * 实现互斥锁（计数器为 1）
 * 我们也可以创建计数为 1 的 Semaphore，将其作为一种类似互斥锁的机制，这也叫二元信号量，
 * 表示两种互斥状态。
 * Semaphore 与 ReentrantLock
 * Semaphore 基本能完成 ReentrantLock 的所有工作，使用方法也与之类似，通过 acquire()与
 * release()方法来获得和释放临界资源。经实测，Semaphone.acquire()方法默认为可响应中断锁，
 * 与 ReentrantLock.lockInterruptibly()作用效果一致，也就是说在等待临界资源的过程中可以被
 * Thread.interrupt()方法中断。
 * 此外，Semaphore 也实现了可轮询的锁请求与定时锁的功能，除了方法名 tryAcquire 与 tryLock
 * 不同，其使用方法与 ReentrantLock 几乎一致。Semaphore 也提供了公平与非公平锁的机制，也
 * 可在构造函数中进行设定。
 * Semaphore 的锁释放操作也由手动进行，因此与 ReentrantLock 一样，为避免线程因抛出异常而
 * 无法正常释放锁的情况发生，释放锁的操作也必须在 finally 代码块中完成。
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/6/18 15:32
 */
@Slf4j
public class SemapOhoreDemo {

    public static void main(String[] args) throws InterruptedException {

        //创建一个计数阈值为5的信号量对象
        //只有5个线程能同时访问
        Semaphore semaphore =new Semaphore(5);

        Thread[] threads =new Thread[10];
        for (int i=0;i<threads.length;i++){
            threads[i]= new Thread(() -> doString(semaphore));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        log.info("任务执行完毕");
    }

    public static int doString(Semaphore semaphore){

        try {
            //申请许可
            semaphore.acquire();
            try {
                Thread.sleep(3000);
                //业务逻辑
                log.info(Thread.currentThread().getName()+"执行业务逻辑"+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放许可
                semaphore.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return semaphore.getQueueLength();
    }
}
