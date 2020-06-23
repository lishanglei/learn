package com.learn;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/10              lishanglei      v1.0.0           Created
 */
@Slf4j
@SpringBootApplication()
public class FilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilterApplication.class, args);
        log.info("=============================================过滤器*监听器*拦截器 服务启动=============================================");
    }
}
