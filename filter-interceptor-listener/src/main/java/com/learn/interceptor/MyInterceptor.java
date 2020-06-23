package com.learn.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

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
public class MyInterceptor implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandler()方法在请求到达controller之前调用");
        Map map =(Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        log.info("[{}]",map.get("name"));
        log.info(request.getParameter("username"));
        if("zhangsan".equals(map.get("name"))){
            return true;
        }else{

            PrintWriter printWriter =response.getWriter();
            printWriter.write("please login again");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("postHandler()方法在return  ModelAndView之前被调用");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("afterCompletion()方法在过滤器返回给前端之前被调用,即在DispatcherServlet渲染视图之后执行");
    }
}
