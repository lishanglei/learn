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
public class Cooker extends Thread {

    @Override
    public void run() {

        while (true) {
            synchronized (Desk.lock) {
                if (Desk.count == 0) {
                    break;
                } else {
                    if (!Desk.flag) {
                        //没有
                        System.out.println("厨师在制作汉堡包");
                        Desk.flag = true;
                        Desk.lock.notifyAll();
                    } else {
                        //有就等待
                        //使用什么对象当作锁,那么必须用这个对象去调用等待和唤醒的方法
                        try {
                            Desk.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
