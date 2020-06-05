package com.learn.jvm.load;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/29
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/29              lishanglei      v1.0.0           Created
 */
public class CInit {
    //static int i=10;  30
    static{
        i=30;  //给变量赋值可以编译通过
        //System.out.println(i); //编译报错,非法向前引用
    }
    static int i=10;  //声明变量     10
    static void print(){
        System.out.println(i);
    }

    public static void main(String[] args) {
        print();
    }
}
