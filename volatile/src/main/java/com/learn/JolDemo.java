package com.learn;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/15
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/15              lishanglei      v1.0.0           Created
 */
public class JolDemo {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
