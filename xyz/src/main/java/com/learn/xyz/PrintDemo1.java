package com.learn.xyz;

import java.util.concurrent.TimeUnit;

/**
 * 用三个线程，每个线程各打印一个字符，按照XYZ的顺序打印10遍
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/8/12
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/8/12              lishanglei      v1.0.0           Created
 */
public class PrintDemo1 {

    private static volatile Integer Count = 0;
    private static volatile Integer a = 0;

    public static void main(String[] args) {

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (a == 0) {
                        System.out.print("X");
                        a = 1;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (a == 1) {
                        System.out.print("Y");
                        a = 2;
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread three = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (a == 2) {
                        System.out.print("Z");
                        System.out.println();
                        a = 0;
                        Count++;
                        if (Count == 10) {
                            System.exit(0);
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        one.start();
        two.start();
        three.start();
    }
}
