package com.kingdee.patchcheck.config;

import com.kingdee.patchcheck.Handle.SessionInterceptor;
import org.springframework.cglib.transform.impl.AddInitTransformer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * description: WebConfig <br>
 * date: 2020\1\8 0008 9:22 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 默认登录页
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Resource
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 自定义拦截器，添加拦截路径和排除拦截路径
        //registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/","/loginIn");
    }
}
