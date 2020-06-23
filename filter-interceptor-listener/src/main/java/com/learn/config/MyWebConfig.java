package com.learn.config;

import com.learn.filter.MyFilter;
import com.learn.interceptor.MyInterceptor;
import com.learn.listener.MyHttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/6/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/6/10              lishanglei      v1.0.0           Created
 */
@Configuration
@Slf4j
public class MyWebConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        log.info("配置类addViewControllers()方法被调用");
        registry.addViewController("/zxc/foo").setViewName("foo");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

      log.info("配置类addInterceptors()方法被调用");
      registry.addInterceptor(new MyInterceptor()).addPathPatterns("/asd/**");

    }

    @SuppressWarnings({"rawtypes","unchecked"})
    @Bean
    public FilterRegistrationBean filterRegist(){

        FilterRegistrationBean filterRegistrationBean =new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        log.info("filter[{}]",filterRegistrationBean);
        return filterRegistrationBean;
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean listenerRegist() {
        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("listener");
        return srb;
    }


}
