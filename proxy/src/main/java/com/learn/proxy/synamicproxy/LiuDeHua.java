package com.learn.proxy.synamicproxy;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/9
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/9              lishanglei      v1.0.0           Created
 */
public class LiuDeHua implements Star {


    @Override
    public String sing() {

        System.out.println("在唱给我一杯忘情水");
        return "唱完";
    }

    @Override
    public String dance() {

        System.out.println("在跳开心的马骝");
        return "跳完";
    }
}
