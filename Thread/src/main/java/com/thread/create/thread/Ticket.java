package com.thread.create.thread;

import java.util.concurrent.Callable;

/**
 * 某电影院正在上映国产大片,共有100张票,而它有三个窗口卖票,设计一个程序
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class Ticket implements Callable<String> {

    //票的数量
    private int ticket = 20;


    @Override
    public String call() throws Exception {

        while (true) {
            synchronized (this) {
                if (ticket == 0) {
                    System.out.println(Thread.currentThread().getName() + "票卖完了");
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName() + " 在卖票,还剩下 " + --ticket);
                }
            }

        }
        return "票卖完了";
    }
}
