package com.learn.zk.pack2;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ConnectStringParser;
import org.apache.zookeeper.client.StaticHostProvider;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.data.Stat;
import org.omg.CORBA.Current;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description : 分布式锁
 * @date 2021/7/21 9:33
 */
public class DistributeLock {

    private String connectString = "192.168.88.52:2181,192.168.88.104:2181,192.168.88.53:2181";
    //会话超时时间 毫秒

    private int sessionTimeout = 2000;

    private ZooKeeper zkClient;

    private CountDownLatch connectLatch = new CountDownLatch(1);

    private String waitPath;

    private CountDownLatch waitLatch = new CountDownLatch(1);

    String currentNode;
    @SneakyThrows
    public  void distributedLock() {

        //1.获取连接
        this.getConnect();

        //2.判断根节点locks是否存在
        Stat exists = zkClient.exists("/locks", false);
        if (exists == null) {
            zkClient.create("/locks", "locks".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }


    }


    /**
     * 对zk加锁
     */
    @SneakyThrows
    public void zkLock() {

        //创建对应的临时顺序节点
         currentNode= zkClient.create("/locks/" + "seq-", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("currentNode:"+currentNode);
        //判断当前节点是否是最小节点,如果是则获取到锁,如果不是则监听前一个节点
        List<String> children = zkClient.getChildren("/locks", false);
        if (children.size() == 1) {
            return;
        } else {
            Collections.sort(children);
            System.out.println(Thread.currentThread().getName()+"children: " + children);
            String thisNode = currentNode.substring("/locks/".length());
            System.out.println(Thread.currentThread().getName()+"thisNode: " + thisNode);
            //通过seq-00000000获取该节点在children集合的位置
            int index = children.indexOf(thisNode);
            System.out.println(index);
            if (index == -1) {
                System.out.println("数据异常");
            } else if (index == 0) {
                return;
            } else {
                //监听前一个节点
                waitPath = "/locks/" + children.get(index - 1);
                zkClient.getData(waitPath, true, null);
                waitLatch.await();
            }

        }
    }


    /**
     * 对zk解锁
     */
    @SneakyThrows
    public void zkUnlock() {

        zkClient.delete(currentNode,-1);
        //删除节点
    }


    /**
     * 连接zk集群
     *
     * @throws IOException
     */
    @SneakyThrows
    private ZooKeeper getConnect() {
        ZKClientConfig zkClientConfig = new ZKClientConfig();
        zkClientConfig.setProperty(ZKClientConfig.ZOOKEEPER_SERVER_PRINCIPAL, "zookeeper/" + connectString);

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent watchedEvent) {

                //监听
                if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                    connectLatch.countDown();
                }
                if (watchedEvent.getType() == Event.EventType.NodeDeleted && watchedEvent.getPath().equals(waitPath)) {
                    waitLatch.countDown();
                }
            }
        }, false, new StaticHostProvider(new ConnectStringParser(connectString).getServerAddresses()), zkClientConfig);

        //等待zk连接成功
        connectLatch.await();
        return zkClient;
    }
}
