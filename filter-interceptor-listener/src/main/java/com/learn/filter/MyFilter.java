package com.learn.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

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
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("servlet容器启动时调用init()方法");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        log.info("在进入web容器后,还未进入servlet容器前执行doFilter()方法");
        log.info(servletRequest.getParameter("name"));
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper =
                new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        if (request.getRequestURI().indexOf("/index") != -1
                || request.getRequestURI().indexOf("/asd") != -1
                || request.getRequestURI().indexOf("/online") != -1
                || request.getRequestURI().indexOf("/login") != -1) {

            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            wrapper.sendRedirect("/login");
        }

    }

    @Override
    public void destroy() {

        log.info("servlet容器销毁时调用destroy()方法");
    }

}
