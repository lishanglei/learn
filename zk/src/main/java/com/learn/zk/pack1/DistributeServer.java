package com.learn.zk.pack1;

import lombok.SneakyThrows;
import org.apache.zookeeper.*;
import org.apache.zookeeper.client.ConnectStringParser;
import org.apache.zookeeper.client.StaticHostProvider;
import org.apache.zookeeper.client.ZKClientConfig;

import java.io.IOException;
import java.util.Random;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/20 17:27
 */
public class DistributeServer {
    private String connectString = "192.168.88.52:2181,192.168.88.104:2181,192.168.88.53:2181";
    //会话超时时间 毫秒
    private int sessionTimeout = 2000;

    ZooKeeper zkClient;
    public static void main(String[] args) {

        //1.获取zk连接
        DistributeServer server = new DistributeServer();
        server.getConnect();

        //2.注册服务器到zk集群
        server.regist(new Random().nextInt()+"");
        //3.启动业务逻辑
        server.business();
    }

    @SneakyThrows
    private void business() {

        Thread.sleep(Long.MAX_VALUE);

    }

    /**
     * 将服务注册到zk集群
     */
    @SneakyThrows
    private void regist(String hostName)  {

        zkClient.create("/servers/"+hostName,hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(hostName+" online");
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

            }
        }, false, new StaticHostProvider(new ConnectStringParser(connectString).getServerAddresses()), zkClientConfig);

        return zkClient;
    }
}
