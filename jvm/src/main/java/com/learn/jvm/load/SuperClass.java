package com.learn.jvm.load;


/**
 * 通过子类引用父类的静态姿态,不会导致子类初始化()
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/5/29
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/5/29              lishanglei      v1.0.0           Created
 */
public class SuperClass {
    static {
        System.out.println("superClass init");
    }
    public static int value =123;

    public static void main(String[] args) {
        //System.out.println(SubClass.value);

        SuperClass[] superClasses =new SuperClass[10];
        System.out.println(superClasses);
    }
}

class SubClass extends  SuperClass{

    static {
        System.out.println("subClass init");
    }
}

