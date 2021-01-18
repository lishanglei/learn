package com.learn;

/**
 * volatile并不能保证并发编程的原子性
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/8/12
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/8/12              lishanglei      v1.0.0           Created
 */
public class VolatileDemo2 {

    //使用volatile修饰,保证内存可见性
    public static volatile int num = 0;

    public static synchronized void increment() {
        num++;
    }

    public static void main(String[] args) throws InterruptedException {

        //10线程,每个线程对num增加1000次,理论上最后num应该是10000
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        increment();
                    }
                }
            });
            threads[i].start();
        }

        //join方法,保证这是个线程跑完之后再输出num的值
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println(num);

    }
}
