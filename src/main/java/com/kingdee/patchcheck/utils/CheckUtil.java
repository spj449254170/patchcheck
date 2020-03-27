package com.kingdee.patchcheck.utils;

import com.kingdee.patchcheck.model.User;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 校验工具类
 */
public class CheckUtil {
    /**
     * 登录校验
     */
    public static Boolean checklogin(HttpServletResponse response, HttpServletRequest request) {
        User users = (User) request.getSession().getAttribute("users");
        if (StringUtils.isEmpty(users)) {
            return false;
        } else {
            return true;
        }
    }
}
