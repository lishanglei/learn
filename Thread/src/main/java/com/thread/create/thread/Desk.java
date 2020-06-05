package com.thread.create.thread;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class Desk {

    //true :有汉堡包,允许吃货执行
    //false :没有汉堡包,允许初始执行
    public static boolean flag =false;

    //制作的总数
    public static int count=10;

    public static final Object lock =new Object();

}
