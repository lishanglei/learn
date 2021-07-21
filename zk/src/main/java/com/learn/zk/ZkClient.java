package com.learn.zk;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ConnectStringParser;
import org.apache.zookeeper.client.StaticHostProvider;
import org.apache.zookeeper.client.ZKClientConfig;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/20 15:03
 */
public class ZkClient {

    private String connectString = "192.168.88.52:2181,192.168.88.104:2181,192.168.88.53:2181";
    //会话超时时间 毫秒
    private int sessionTimeout = 2000;
    private ZooKeeper zkClient = null;

    @Before
    public void init() throws IOException, InterruptedException {

        ZKClientConfig zkClientConfig = new ZKClientConfig();
        zkClientConfig.setProperty(ZKClientConfig.ZOOKEEPER_SERVER_PRINCIPAL,"zookeeper/"+connectString);

        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children = zkClient.getChildren("/", true);
                children.forEach(System.out::println);
            }
        },false,new StaticHostProvider(new ConnectStringParser(connectString).getServerAddresses()),zkClientConfig);

        while (true) {
            if (ZooKeeper.States.CONNECTING == zkClient.getState()) {
                Thread.sleep(300);
            } else {
                System.out.println("zk连接成功" + zkClient.getState());
                System.out.println();
                System.out.println();
                break;
            }
        }
    }

    @After
    public void destory() throws InterruptedException {
        zkClient.close();
    }

    @Test
    public void create() throws KeeperException, InterruptedException {

        String nodeCreated = zkClient.create("/lol", "艾欧尼亚".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("========" + nodeCreated);
    }

    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zkClient.getChildren("/locks", true);

        System.out.println("==================");
        children.forEach(System.out::println);

         Thread.sleep(Long.MAX_VALUE);
    }

    @Test
    public void exit() throws KeeperException, InterruptedException {
        Stat exists = zkClient.exists("/lol", false);
        System.out.println(exists==null?"不存在":"存在");
    }
}
