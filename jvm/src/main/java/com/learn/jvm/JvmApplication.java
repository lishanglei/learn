package com.learn.jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/29
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/29              lishanglei      v1.0.0           Created
 */
@SpringBootApplication
public class JvmApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvmApplication.class,args);
        System.out.println("***********************************jvm server启动*****************************");
    }

}
