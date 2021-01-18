package com.learn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * volatile可以保证并发编程的有序性
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/8/12
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/8/12              lishanglei      v1.0.0           Created
 */
public class VolatileDemo3 {
    static int x = 0;
    static int y = 0;

    public static void main(String[] args) throws InterruptedException {

        Set<String> resultSet = new HashSet<>();
        Map<String, Integer> resultMap = new HashMap<>();

        for (; ; ) {
            x = 0;
            y = 0;
            resultMap.clear();
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    int a = y;
                    x = 1;
                    resultMap.put("a", a);

                }
            });


            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    int b = x;
                    y = 1;
                    resultMap.put("b", b);
                }
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            resultSet.add("a=" + resultMap.get("a") + "," + "b=" + resultMap.get("b"));

            System.out.println(resultSet);
        }

    }
}
