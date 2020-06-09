package com.learn.proxy.synamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/9
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/9              lishanglei      v1.0.0           Created
 */
public class StarProxy1 implements InvocationHandler {

    private Object target;

    public StarProxy1(Object target) {
        this.target = target;
    }

    /**
     * @param proxy  代理对象
     * @param method 调度方法
     * @param args   调度方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        proxy = target;
        System.out.println("洽谈导演");
        return method.invoke(proxy, args);
    }
}
