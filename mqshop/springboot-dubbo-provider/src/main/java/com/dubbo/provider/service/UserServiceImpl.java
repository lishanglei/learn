package com.dubbo.provider.service;

import com.dubbo.IUserService;
import org.springframework.stereotype.Service;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/19
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/19              lishanglei      v1.0.0           Created
 */
@Service
@com.alibaba.dubbo.config.annotation.Service(interfaceClass = IUserService.class)
public class UserServiceImpl implements IUserService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
