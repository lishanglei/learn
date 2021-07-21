package com.learn.zk.pack3;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :curator框架的分布式锁
 * @date 2021/7/21 10:29
 */
public class CuratorLockTest {

    private static  String connectString = "192.168.88.52:2181,192.168.88.104:2181,192.168.88.53:2181";
    //会话超时时间 毫秒

    private static int sessionTimeout = 2000;


    public static void main(String[] args) {

        //创建分布式锁
        InterProcessMutex lock1 = new InterProcessMutex(getCuratorFramework(), "/locks");
        InterProcessMutex lock2 = new InterProcessMutex(getCuratorFramework(), "/locks");
        new Thread(() -> {

            try {
                lock1.acquire();
                System.out.println("线程1 启动  获取到锁");

                lock1.acquire();
                System.out.println("线程1  再次获取到锁");

                Thread.sleep(5 * 1000);

                lock1.release();
                System.out.println("线程1 释放锁");

                lock1.release();
                System.out.println("线程1 再次释放锁");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();

        new Thread(() -> {

            try {
                lock2.acquire();
                System.out.println("线程2 启动  获取到锁");

                lock2.acquire();
                System.out.println("线程2  再次获取到锁");

                Thread.sleep(5 * 1000);

                lock2.release();
                System.out.println("线程2 释放锁");

                lock2.release();
                System.out.println("线程2再次释放锁");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();


    }

    // 分布式锁初始化
    public static CuratorFramework getCuratorFramework (){
        //重试策略，初试时间 3 秒，重试 3 次
        RetryPolicy policy = new ExponentialBackoffRetry(3000, 3);
        //通过工厂创建 Curator'
        CuratorFramework client =
                CuratorFrameworkFactory.builder()
                        .connectString(connectString)
                        .connectionTimeoutMs(sessionTimeout)
                        .sessionTimeoutMs(sessionTimeout)
                        .retryPolicy(policy).build();
        //开启连接
        client.start();
        System.out.println("zookeeper 初始化完成...");
        return client;
    }
}
