package com.thread.create.thread;


import java.util.concurrent.Callable;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class ImplementsCallable implements Callable<String> {

    @Override
    public String call() throws Exception {

        for(int i=0;i<20;i++){
            System.out.println("实现Callable接口创建的线程开启了"+i);
        }
        //返回值就表示线程运行完毕之后的结果
        return "实现Callable接口创建的线程运行完毕";
    }
}
