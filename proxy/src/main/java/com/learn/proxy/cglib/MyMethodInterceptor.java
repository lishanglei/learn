package com.learn.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

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
public class MyMethodInterceptor implements MethodInterceptor {

    /**
     *
     * @param o             要进行增强的对象
     * @param method        拦截的方法
     * @param objects       参数列表:基本数据类型需要传入其包装类型
     * @param methodProxy   对方法的代理,invokeSuper标识对被代理对象方法的调用
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o,
                            Method method,
                            Object[] objects,
                            MethodProxy methodProxy
    ) throws Throwable {

        System.out.println("这里对目标类进行加强");
        //采用方法调用,而非反射
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }
}
