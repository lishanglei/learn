package create.thread;

import com.thread.create.thread.*;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/5
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/5              lishanglei      v1.0.0           Created
 */
public class DemoTest {

    @Test
    public void test1() {

        ExtendsThread thread = new ExtendsThread();
        ExtendsThread thread1 = new ExtendsThread();
        thread.start();
        thread1.start();
    }

    @Test
    public void test2() {
        ImplementsRunnable runnable = new ImplementsRunnable();
        ImplementsRunnable runnable1 = new ImplementsRunnable();
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        thread.start();
        thread1.start();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {

        ImplementsCallable callable = new ImplementsCallable();
        FutureTask<String> futureTask = new FutureTask<>(callable);
        FutureTask<String> futureTask1 = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        Thread thread1 = new Thread(futureTask1);
        thread.start();
        thread1.start();
        //获取线程运行结果,如果线程运行结束,get()方法会一直死等
        String result = futureTask.get();
        System.out.println(result);
    }

    @Test
    public void test4() {
        Ticket ticket = new Ticket(); //一个实例,三个线程
        FutureTask futureTask1 = new FutureTask(ticket);
        FutureTask futureTask2 = new FutureTask(ticket);
        FutureTask futureTask3 = new FutureTask(ticket);

        Thread t1 = new Thread(futureTask1, "窗口一");
        Thread t2 = new Thread(futureTask2, "窗口二");
        Thread t3 = new Thread(futureTask3, "窗口三");

        t1.start();
        t2.start();
        t3.start();

    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        FutureTask futureTask1 = new FutureTask(ticket1);
        FutureTask futureTask2 = new FutureTask(ticket2);

        Thread t1 = new Thread(futureTask1, "窗口一");
        Thread t2 = new Thread(futureTask2, "窗口二");

        t1.start();
        t2.start();

    }


    @Test
    public void test6() {

        Cooker cooker = new Cooker();
        Food food = new Food();
        cooker.start();
        food.start();
    }
}
