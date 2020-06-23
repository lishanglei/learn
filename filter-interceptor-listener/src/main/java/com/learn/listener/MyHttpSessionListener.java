package com.learn.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/10              lishanglei      v1.0.0           Created
 */
@Slf4j
public class MyHttpSessionListener implements HttpSessionListener {

    public static int online = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        log.info("创建session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        log.info("销毁session");

    }
}
