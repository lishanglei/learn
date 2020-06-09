package com.learn.proxy.synamicproxy;

import lombok.Data;

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
@Data
public class ObjectProxy implements InvocationHandler {  //通用代理对象

    //目标类,就是被代理对象
    private Object target;

    public ObjectProxy(Object target) {
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

        proxy=target;
        //这里可以增强
        System.out.println("收钱");
        Object obj =method.invoke(proxy,args);
        return obj;
    }
}
