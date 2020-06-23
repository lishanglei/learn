package com.dubbo.comnsumer.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dubbo.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/22 @Description Modification History: Date Author Version Description
 * ---------------------------------------------------------------------------------* 2020/6/22
 * lishanglei v1.0.0 Created
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return this.userService.sayHello("小明");
    }
}
