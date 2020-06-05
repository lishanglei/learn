package com.thread.create.thread.lock;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class DeadLock {

    public static void main(String[] args) {

        Object a=new Object();
        Object b=new Object();

        new Thread(()->{
            while (true){
                synchronized (a){
                    synchronized (b){
                        System.out.println("小康同学正在过马路");
                    }
                }
            }
        }).start();

        new Thread(()->{
            while (true){
                synchronized (b){
                    synchronized (a){
                        System.out.println("小薇同学正在过马路");
                    }
                }
            }
        }).start();
    }
}
