package com.learn.memory.thread;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/29
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/29              lishanglei      v1.0.0           Created
 */
public class voliteDemo extends Thread{

    private static boolean flag = false;

    private static int a =123;
    private  int b ;

    public voliteDemo(int b){
        this.b =b;
    }


    @Override
    public void run() {

        a=b;
    }

    private static void change() {
        System.out.println("123");
        flag = true;
        System.out.println("456");
    }

    private static void keep() {

        while (!flag) {
            System.out.println("1");
        }

        System.out.println("flag:" + flag);
    }

    public static void main(String[] args) {

//        Thread thread = new Thread(() -> {
////            keep();
////        });
////        thread.start();
////        try {
////            Thread.sleep(1000);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////
////        Thread thread1 = new Thread(() -> {
////
////            change();
////        });
////        thread1.start();



        voliteDemo voliteDemo = new voliteDemo(132);

    }
}
