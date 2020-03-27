package com.kingdee.patchcheck.config;

import com.kingdee.patchcheck.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("执行了AdminInterceptor的preHandle方法");
        try {
            //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
            User user=(User)request.getSession().getAttribute("users");
            if(user!=null){
                return true;
            }
            response.sendRedirect(request.getContextPath()+"/index.html#/");

        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果设置为false时，被请求时，拦截器执行到此处将不会继续操作
        //如果设置为true时，请求将会继续执行后面的操作
        return false;
    }
}
