package com.kingdee.patchcheck.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.utils.CheckUtil;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * description: itemController <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 项目controller
 */

@RestController
@RequestMapping("/item")
@Api(value = "测试swagger", description = "测试swagger api")
public class itemController {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IitemService itemservice;

    //分页获取项目数据
    @GetMapping(value = "/getitme")
    public Result<Item> admin(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletResponse response, HttpServletRequest request) {
        logger.info("分页获取项目数据，入参page:{},size:{},response:{},request:{}",page,size,response,request);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<Item> getitem = itemservice.getitem(page, size);
        logger.info("分页获取项目数据，出参getitem：{}",getitem.toString());
        return ResultUtil.success(getitem);
    }

    //新增项目
    @PostMapping(value = "/additem")
    public Result additem(Item item, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(item.toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        return ResultUtil.success(itemservice.additem(item,users));


    }
    //修改项目
    @PostMapping(value = "/updateitem")
    public Result updateitem(Item item, HttpServletResponse response, HttpServletRequest request) {
        logger.info("项目修改，入参item:{},response:{},request:{}",item.toString(),response,request);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        Boolean result = itemservice.updateitem(item);
        logger.info("项目修改，出参result：{}",result);
        return ResultUtil.success(result);


    }

    //删除项目
    @GetMapping(value = "/deleteitem/{id}")
    public Result<Boolean> deleteitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        logger.info("项目删除，入参id：{}",id);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        Boolean result = itemservice.deleteitem(id);
        logger.info("项目删除，出参result:{} ",result);
        return ResultUtil.success(result);


    }
    //关闭项目
    @GetMapping(value = "/closeitem/{id}")
    public Result<Boolean> closeitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        logger.info("关闭项目 入参id:{}",id);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        Boolean result = itemservice.closeitem(id);
        logger.info("关闭项目，出参result:{}",request);
        return ResultUtil.success(result);


    }
    //通过编号查找项目
    @GetMapping(value = "/getitembyid/{id}")
    public Result<Item> getitembyid(@PathVariable("id")Integer id,HttpServletResponse response, HttpServletRequest request) {
        logger.info("通过编号查找项目，入参id:{}",id);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            Optional<Item> item = itemservice.getitembyid(id);
            logger.info("通过编号查找项目 出参:{}",item.toString());
            return ResultUtil.success(item);
        }
    }
    //通过项目名称查找项目
    @GetMapping(value = "/getitembyname")
    public Result<Item> getitembyname(@RequestParam("name") String name,HttpServletResponse response, HttpServletRequest request) {
        logger.info("通过项目名称找项目 入参：name：{}",name);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            Page<Item> item = itemservice.getitembyname(name);
            logger.info("通过项目名称找项目 出参：item",item.toString());
            return ResultUtil.success(item);
        }
    }
    //获取用户列表
    @GetMapping(value = "/userlist")
    public Result<User> userlist(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        logger.info("项目获取用户列表，入参 id：{}",id);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            Page<User> users = itemservice.getitemuserlist(id);
            logger.info("项目获取用户列表，出参 users:{}",users);
            return ResultUtil.success(users);
        }
    }
    //添加用户
    @PostMapping(value = "/adduser")
    public Result<Boolean> adduser(@RequestParam("user") String user, @RequestParam("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {
        logger.info("项目添加用户 入参 user:{},itemid:{}",user,itemid);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            Boolean result = itemservice.adduser(user,itemid);
            logger.info("项目添加用户，出参 result:{}",result);
            return ResultUtil.success(result);
        }

    }
    //获取不在这个项目的普通用户信息
    @GetMapping(value = "/getusernoitem")
    public Result<User> getusernoitem(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        logger.info("获取不在这个项目的普通用户信息 入参id:{}",id);
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            Page<User> user = itemservice.getusernoinitem(id);
            logger.info("获取不在这个项目的普通用户信息 出参:{}",user);
            return ResultUtil.success(user);
        }
    }
   /* //获取删除在这个项目的人员
    @GetMapping(value = "/deleteuser")
    public Result<Boolean> deleteuser(@RequestParam("itemid") Integer itemid,@RequestParam("userid") Integer userid,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.deleteuser(userid,itemid));
        }
    }

    public static Boolean checklogin(HttpServletResponse response, HttpServletRequest request) {
        User users = (User) request.getSession().getAttribute("users");
        if (StringUtils.isEmpty(users)) {
            return false;
        } else {
            return true;
        }
    }*/

}
