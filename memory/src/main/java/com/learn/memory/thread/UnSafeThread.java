package com.learn.memory.thread;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/26
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/26              lishanglei      v1.0.0           Created
 */
public class UnSafeThread extends Thread{

    private static int ticket= 100;
    private static String threadName;

    public UnSafeThread(){
        this.threadName=Thread.currentThread().getName();
    }

    @Override
    public void run() {
        synchronized (UnSafeThread.class){
            for(int i=0;i<ticket;i++){

                System.out.println(threadName+"抢到了第["+ticket--+"]张票"+i);
            }
        }

    }

    public static void main(String[] args) {
        UnSafeThread unSafeThread=new UnSafeThread();
        Thread thread0 =new Thread(unSafeThread,"线程一");
        Thread thread1 =new Thread(unSafeThread,"线程二");
        Thread thread2 =new Thread(unSafeThread,"线程三");
        thread0.start();
        thread1.start();
        thread2.start();
    }
}
