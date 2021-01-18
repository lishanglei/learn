import java.lang.ref.SoftReference;

/**
 * 软引用
 * 比较适合于往内存中加载图片,图片比较占用内存,如果多次垃圾回收可以去除图片
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/16
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/16              lishanglei      v1.0.0           Created
 */
public class T02_SoftReference {

    //-Xmx20M 堆内存最大20m
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);//10m

        System.out.println(m.get());

        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //再分配一个数组,heap将装不下,这个时候系统会垃圾回收,先回收一次,如果不够,会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 15];//15m
        System.out.println(m.get());
    }
}
