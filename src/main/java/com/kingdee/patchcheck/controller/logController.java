package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.model.patchLog;
import com.kingdee.patchcheck.service.IfileService;
import com.kingdee.patchcheck.service.IlogService;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: logController <br>
 * date: 2020\2\4 0008 15:24 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 日志管理controller
 */

@RestController
@RequestMapping("/log")
@Api(value = "测试swagger", description = "测试swagger api")
public class logController {
    @Autowired
    private IlogService IlogService;
    @GetMapping(value = "/patchlog")
    public Result<Page> getpatchlog(HttpServletResponse response, HttpServletRequest request){
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        Page<patchLog> patchlog =  IlogService.getpatchlog();

        return ResultUtil.success(patchlog);
        /*if(istion){
            return ResultUtil.success(ifileService.fileUpload(file,name,pacthid,type,remarks,users,true));
        }else{
            return ResultUtil.success(ifileService.fileUpload(file,name,pacthid,type,remarks,users,false));
        }*/

    }
    //分页获取用户数据
    @GetMapping(value = "/getpatchlog/{page}/{size}")
    public Result<Page> getpatchlog(@PathVariable("page") int page, @PathVariable("size") int size, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<patchLog> getpatchLog = IlogService.getpatchlog(page, size);
        return ResultUtil.success(getpatchLog);
    }

    public static Boolean checklogin(HttpServletResponse response, HttpServletRequest request) {
        User users = (User) request.getSession().getAttribute("users");
        if (StringUtils.isEmpty(users)) {
            return false;
        } else {
            return true;
        }
    }

}
