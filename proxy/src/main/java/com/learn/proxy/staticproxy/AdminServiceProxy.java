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
public class AdminServiceProxy implements AdminService {

    private AdminService adminService;

    public AdminServiceProxy(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public void update() {
        System.out.println("判断用户是否有权限进行更新操作");
        adminService.update();
        System.out.println("记录用户执行的更新操作");
    }

    @Override
    public Object find() {

        System.out.println("判断用户是否有权限进行数据查询操作");
        System.out.println("记录用户执行的数据查询操作");
        return adminService.find();
    }
}
