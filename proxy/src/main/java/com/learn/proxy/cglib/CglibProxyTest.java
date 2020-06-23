package com.learn.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/9
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/9              lishanglei      v1.0.0           Created
 */
public class CglibProxyTest {

    public static void main(String[] args) {

        //在指定目录下生成动态代理类,我们可以反编译看一下里面的内容
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"D:\\java\\java_workspace");
        //创建Enhancer对象,类似于JDK代理的proxy类,下一步设置几个参数
        Enhancer enhancer =new Enhancer();
        //设置目标类的字节码文件
        enhancer.setSuperclass(Dog.class);
        //设置回调函数
        enhancer.setCallback(new MyMethodInterceptor());

        //这里的create()方法是真是的创建代理类
        Dog proxyDog =(Dog)enhancer.create();

        //调用代理类的eat方法
        proxyDog.eat();

    }
}
