package com.learn.proxy.synamicproxy;

import org.junit.Test;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/9
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/9              lishanglei      v1.0.0           Created
 */
public class DynamicProxyTest {

    @Test
    public void test1(){

        LiuDeHua star =new LiuDeHua();
        ObjectProxy proxy =new ObjectProxy(star);
        Object obj =ProxyFactory.createProxyObj(star,proxy);
        Star s =(Star)obj;
        s.dance();
    }
}
