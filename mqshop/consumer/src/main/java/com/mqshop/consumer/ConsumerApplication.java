package com.mqshop.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class,args);
        log.info("==============================springboot rocketmq consumer started ==============================");
    }

}
