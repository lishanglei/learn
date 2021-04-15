package com.learn.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @description:
 * @author: lishanglei
 * @createDate: 2021/4/1
 */
public class Demo1 {

    public static void main(String[] args) throws NacosException, InterruptedException {
        //nacos 地址
        String serverAddr = "127.0.0.1:8848";
        // Data Id
        String dataId = "test";
        //Group
        String group = "DEFAULT_GROUP";

        Properties properties =new Properties();

        properties.put("serverAddr",serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("接收到配置发生改变时的操作:" + configInfo);
            }

            @Override
            public Executor getExecutor() {
                return null;
            }
        });

//        boolean isPublishOk = configService.publishConfig(dataId, group, "content");
//        System.out.println(isPublishOk);
//
//        Thread.sleep(3000);
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//
//        boolean isRemoveOk = configService.removeConfig(dataId, group);
//        System.out.println(isRemoveOk);
//        Thread.sleep(3000);
//
//        content = configService.getConfig(dataId, group, 5000);
//        System.out.println(content);
//        Thread.sleep(300000);
    }

}
