package com.dubbo.comnsumer;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/22 @Description Modification History: Date Author Version Description
 *     ---------------------------------------------------------------------------------* 2020/6/22
 *     lishanglei v1.0.0 Created
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ConsumerApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerApplication.class, args);
    System.out.println(
        "*******************************dubbo-consumer started**************************************");
  }
}
