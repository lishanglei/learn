import com.learn.jvm.load.Singleton;
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
public class DemoTest {


    @Test
    public void test1(){
        Singleton.getInstance();
        System.out.println(Singleton.count1);
        System.out.println(Singleton.count2);
    }
}
