package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.model.patchLog;
import com.kingdee.patchcheck.service.IfileService;
import com.kingdee.patchcheck.service.IlogService;
import com.kingdee.patchcheck.utils.CheckUtil;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IlogService IlogService;
    @GetMapping(value = "/patchlog")
    public Result<Page> getpatchlog(HttpServletResponse response, HttpServletRequest request){
        logger.info("获取日志，入参 无");
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        Page<patchLog> patchlog =  IlogService.getpatchlog();
        logger.info("获取日志，出参 patchlog :{}",patchlog.toString());
        return ResultUtil.success(patchlog);

    }
    //分页获取日志信息
    @GetMapping(value = "/getpatchlog/{page}/{size}")
    public Result<Page> getpatchlog(@PathVariable("page") int page, @PathVariable("size") int size, HttpServletResponse response, HttpServletRequest request) {
        logger.info("分页获取日志信息，入参page:{},size:{}",page,size);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<patchLog> getpatchLog = IlogService.getpatchlog(page, size);
        logger.info("分页获取日志信息，出参getpatchLog：{} ",getpatchLog.toString());
        return ResultUtil.success(getpatchLog);
    }


}
