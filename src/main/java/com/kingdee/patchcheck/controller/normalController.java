package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.service.InormalitemService;
import com.kingdee.patchcheck.utils.CheckUtil;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: normalController <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 普通用户controller
 */

@RestController
@RequestMapping("/normal")
@Api(value = "测试swagger", description = "测试swagger api")
public class normalController {
    @Autowired
    private IitemService itemservice;
    @Autowired
    private InormalitemService inormalitemService;
    //分页获取项目数据
    @GetMapping(value = "/getitme/{page}/{size}")
    public Result<User> getnormalitem(@PathVariable("page") int page, @PathVariable("size") int size, HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        page = page-1;
        Page<Item> getitem = inormalitemService.getitem(page, size,users);
        return ResultUtil.success(getitem);
    }

    /*//新增项目
    @PostMapping(value = "/additem")
    public Result additem(Item item, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(item.toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        return ResultUtil.success(itemservice.additem(item,users));


    }*/
    //修改用户
    /*@PostMapping(value = "/updateitem")
    public Result updateitem(Item item, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(item.toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.updateitem(item));


    }*/

    //删除用户
    /*@GetMapping(value = "/deleteitem/{id}")
    public Result<User> deleteitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.deleteitem(id));


    }*/
    //关闭项目
    /*@GetMapping(value = "/closeitem/{id}")
    public Result<User> closeitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.closeitem(id));


    }*/
    //通过编号查找项目
   /* @GetMapping(value = "/getitembyid/{id}")
    public Result<User> getitembyid(@PathVariable("id")Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            return ResultUtil.success(itemservice.getitembyid(id));
        }
    }*/
    //通过项目名称查找项目
    /*@GetMapping(value = "/getitembyname")
    public Result<User> getitembyname(@RequestParam("name") String name,HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getuserbyname(name));
        }
    }*/
    //获取用户列表
   /* @GetMapping(value = "/userlist")
    public Result<User> userlist(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getitemuserlist(id));
        }
    }*/
    //添加用户
   /* @PostMapping(value = "/adduser")
    public Result<Boolean> adduser(@RequestParam("user") String user, @RequestParam("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {


        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.adduser(user,itemid));
        }

    }*/
    //获取不在这个项目的普通用户信息
   /* @GetMapping(value = "/getusernoitem")
    public Result<Boolean> getusernoitem(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getusernoinitem(id));
        }
    }*/
    //获取删除在这个项目的人员
   /* @GetMapping(value = "/deleteuser")
    public Result<Boolean> deleteuser(@RequestParam("itemid") Integer itemid,@RequestParam("userid") Integer userid,HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.deleteuser(userid,itemid));
        }
    }*/



}
