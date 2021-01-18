package com.learn.xyz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/8/13
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/8/13              lishanglei      v1.0.0           Created
 */
public class PrintDemo2 {

    private volatile int count = 1;
    private volatile int limit = 10;

    private volatile boolean runFlag = true;
    private Lock lock = new ReentrantLock();
    private static Condition[] conditions = new Condition[3];
    private static Thread[] threads = new Thread[3];

    private volatile int curId = 1;

    public static void main(String[] args) {
        PrintDemo2 xyz = new PrintDemo2();
        for (int i = 0; i < 3; i++) {
            conditions[i] = xyz.lock.newCondition();
        }


    }
}
