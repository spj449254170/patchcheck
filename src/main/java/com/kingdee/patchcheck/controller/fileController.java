package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.service.IfileService;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Past;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * description: user <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 文件管理controller
 */

@RestController
@RequestMapping("/file")
@Api(value = "测试swagger", description = "测试swagger api")
public class fileController {
    @Autowired
    private IfileService ifileService;
    @PostMapping(value = "/fileUpload")
    public Result<Boolean> fileUpload(MultipartFile file,@RequestParam("name") String name,@RequestParam("id") Integer pacthid,@RequestParam("type") Integer type,@RequestParam("remarks") String remarks,@RequestParam("delivery")boolean istion,HttpServletResponse response, HttpServletRequest request){
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        if(istion){
            return ResultUtil.success(ifileService.fileUpload(file,name,pacthid,type,remarks,users,true));
        }else{
            return ResultUtil.success(ifileService.fileUpload(file,name,pacthid,type,remarks,users,false));
        }

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
