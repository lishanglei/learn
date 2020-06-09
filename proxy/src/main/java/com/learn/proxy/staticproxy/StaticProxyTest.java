package com.learn.proxy.staticproxy;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/8
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/8              lishanglei      v1.0.0           Created
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        AdminService adminService =new AdminServiceImpl();
        AdminServiceProxy proxy =new AdminServiceProxy(adminService);
        proxy.update();
        System.out.println("===========================");
        proxy.find();
    }
}
