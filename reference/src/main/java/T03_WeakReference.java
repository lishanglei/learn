import java.lang.ref.WeakReference;

/**
 * 弱引用
 * 一次性,碰到gc就会被回收
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/16
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/16              lishanglei      v1.0.0           Created
 */
public class T03_WeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }
}
