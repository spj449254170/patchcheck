package com.kingdee.patchcheck.Handle;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: SessionInterceptor <br>
 * date: 2020\1\8 0008 15:59 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
@Component
public class SessionInterceptor implements HandlerInterceptor  {
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }
    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if ("/".equals(arg0.getRequestURI())) {
            return true;}
        //权限路径拦截
        Object object = arg0.getSession().getAttribute("users");
        if (null == object) {
            arg1.sendRedirect("/patchcheck/");
            return false;}
        return true;
    }
}
