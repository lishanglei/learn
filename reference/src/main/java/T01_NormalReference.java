/**
 * 强引用
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/10/16
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/10/16              lishanglei      v1.0.0           Created
 */
public class T01_NormalReference {

    public static void main(String[] args) {
        M m = new M();
        m = null;
        System.gc();

    }
}
