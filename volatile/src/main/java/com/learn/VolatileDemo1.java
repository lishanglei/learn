package com.learn;

/**
 * 如果不用volatile修饰共享变量,多线程之间的内存不可见性
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/8/12
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/8/12              lishanglei      v1.0.0           Created
 */
public class VolatileDemo1 {

    static volatile boolean flag = false;


    public static void main(String[] args) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread1 waiting flag********************");
                while (!flag) { //只要flag没有变为TRUE,则一直堵塞在这里

                }
                System.out.println("Thread1 success=========================");
            }
        }).start();

        Thread.sleep(2000);//确保第一个线程先执行

        new Thread(new Runnable() {
            @Override
            public void run() {
                changeFlag();
            }
        }).start();

    }

    private static void changeFlag() {

        System.out.println("Thread2 start change Flag*************");
        flag = true;
        System.out.println("Thread2 end change Flag*************");
    }

}
