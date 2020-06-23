package com.mqshop.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/18
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/18              lishanglei      v1.0.0           Created
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ProducerApplication.class})
public class ProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void sendMessage(){

    rocketMQTemplate.convertAndSend("springboot-rocketmq","Hello Springboot Rocket Mq");
}
}
