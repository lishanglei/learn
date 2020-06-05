package com.learn.jvm.load;

import org.junit.Test;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/29
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/29              lishanglei      v1.0.0           Created
 */
public class Singleton {

    //private static Singleton singleton =new Singleton();  //1  0
    public static int count1;
    public static int count2 =0;

    private static Singleton singleton =new Singleton();   //1  1

    public Singleton() {
        count1++;
        count2++;
    }
    public static Singleton getInstance(){
        return singleton;
    }


    @Test
    public void test2(){
        Singleton.getInstance();
        System.out.println(Singleton.count1);
        System.out.println(Singleton.count2);
    }
}
