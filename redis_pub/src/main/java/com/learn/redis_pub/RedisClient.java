package com.learn.redis_pub;


import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/7/7 17:09
 */
public class RedisClient {
    public static void main(String[] args) {

        Set<HostAndPort> clusterNodes =new HashSet<>();
        clusterNodes.add(new HostAndPort("192.168.88.52",9001));
        clusterNodes.add(new HostAndPort("192.168.88.52",9004));
        clusterNodes.add(new HostAndPort("192.168.88.104",9002));
        clusterNodes.add(new HostAndPort("192.168.88.104",9005));
        clusterNodes.add(new HostAndPort("192.168.88.243",9003));
        clusterNodes.add(new HostAndPort("192.168.88.243",9006));

        JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisCluster jedisCluster =new JedisCluster(clusterNodes,6000,5000,10, jedisPoolConfig);
        System.out.println(jedisCluster.set("name","zhangsan"));
        System.out.println(jedisCluster.set("age","18"));
        System.out.println(jedisCluster.get("name"));
        System.out.println(jedisCluster.get("age"));
        jedisCluster.close();
    }
}
