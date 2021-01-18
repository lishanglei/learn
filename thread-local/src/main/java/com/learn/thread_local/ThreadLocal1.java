package com.learn.thread_local;

import java.util.concurrent.TimeUnit;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/16
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/16              lishanglei      v1.0.0           Created
 */
public class ThreadLocal1 {

    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程一:" + tl);

            System.out.println(tl.get());
        }).start();

        new Thread(() -> {
            TimeUnit.SECONDS.toSeconds(1);
            System.out.println("线程二:" + tl);

            tl.set(new Person());
        }).start();
    }

}
