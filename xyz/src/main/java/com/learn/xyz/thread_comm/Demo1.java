package com.learn.xyz.thread_comm;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/28 10:40
 */
public class Demo1 extends Thread {

    public static void main(String[] args) {
//        Demo1 demo1 =new Demo1();
//        demo1.start();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        //demo1.start();
//        demo1.interrupt();
//        while (demo1.isAlive()){
//
//        }
//        System.out.println(demo1.x+"==="+demo1.y);

        for (int i = 0; i < 3000; i++) {
            new Thread(()->{
                Thread thread = Thread.currentThread();
                System.out.println("创建了一个线程"+thread.getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
//    private int x=0,y=0;
//    @Override
//    public void run() {
//        x++;
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//
//        }
//        y++;
//
//    }
}
