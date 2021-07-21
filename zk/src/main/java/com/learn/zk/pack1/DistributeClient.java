package com.learn.zk.pack1;

import lombok.SneakyThrows;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.client.ConnectStringParser;
import org.apache.zookeeper.client.StaticHostProvider;
import org.apache.zookeeper.client.ZKClientConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/20 17:27
 */
public class DistributeClient {

    private String connectString = "192.168.88.52:2181,192.168.88.104:2181,192.168.88.53:2181";
    //会话超时时间 毫秒

    private int sessionTimeout = 2000;

    ZooKeeper zkClient;

    public static void main(String[] args) {

        //1.获取zk连接
        DistributeClient server = new DistributeClient();
        server.getConnect();

        //2.监听 /servers 下面子节点变化
        server.getServersList();

        //3.启动业务逻辑
        server.business();
    }

    /**
     * 获取/servers下所有子节点
     */
    @SneakyThrows
    private void getServersList() {

        List<String> children = zkClient.getChildren("/servers", true);

        ArrayList<String> servers = new ArrayList<>();
        for (String child : children) {
            byte[] data = zkClient.getData("/servers/" + child, false, null);
            servers.add(new String(data));
        }

        System.out.println("子节点列表"+servers);
    }

    @SneakyThrows
    private void business() {

        Thread.sleep(Long.MAX_VALUE);

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
                getServersList();

            }
        }, false, new StaticHostProvider(new ConnectStringParser(connectString).getServerAddresses()), zkClientConfig);

        return zkClient;
    }
}
