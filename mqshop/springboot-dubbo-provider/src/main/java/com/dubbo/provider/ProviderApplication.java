package com.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/19
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/19              lishanglei      v1.0.0           Created
 */
@SpringBootApplication
@EnableDubboConfiguration
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
        System.out.println("*******************************dubbo-provider started**************************************");
    }

}
