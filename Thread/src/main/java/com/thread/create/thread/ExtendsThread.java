package com.thread.create.thread;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class ExtendsThread extends Thread{

    @Override
    public void run() {

        for(int i=0;i<20;i++){
            System.out.println("线程开启了"+i);
        }
    }
}
