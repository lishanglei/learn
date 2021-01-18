/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/16
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/16              lishanglei      v1.0.0           Created
 */
public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
