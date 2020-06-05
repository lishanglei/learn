package com.learn.jvm.stack;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/1              lishanglei      v1.0.0           Created
 */
public class GCTest {

    private static final int a =1024*1024;
    private byte[] bigSize =new byte[2*a];
    private Object instance;
    public static void main(String[] args) {
        GCTest gcTest =new GCTest();
        GCTest gcTest1 =new GCTest();

        gcTest.instance=gcTest1;
        gcTest1.instance=gcTest;

        gcTest=null;
        gcTest1=null;

        System.gc();
    }
}
