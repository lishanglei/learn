package com.learn.aqs;

import java.util.Currency;

/**
 * @author lishanglei
 * @version v1.0.0
 * @Description :
 * @date 2021/5/31 11:22
 */
public class LockDemo1Test {

    private static LockDemo1Test lockDemo1Test =new LockDemo1Test();
    public static void main(String[] args) {

        new Thread(()->{
            lockDemo1Test.read(Thread.currentThread());
        }).start();

        new Thread(()->{
            lockDemo1Test.write(Thread.currentThread());
        }).start();

        new Thread(()->{
            lockDemo1Test.read(Thread.currentThread());
        }).start();
    }

    public synchronized void read(Thread thread){

        long start = System.currentTimeMillis();

        while(System.currentTimeMillis()-start<=1){
            System.out.println(thread.getName()+" 正在进行[读]操作");
        }
        System.out.println("读操作执行完毕");
    }

    public synchronized void write(Thread thread){

        long start = System.currentTimeMillis();

        while(System.currentTimeMillis()-start<=1){
            System.out.println(thread.getName()+" 正在进行[写]操作");
        }
        System.out.println("写操作执行完毕");
    }
}
