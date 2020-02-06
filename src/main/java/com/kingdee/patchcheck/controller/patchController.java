package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.Item;
import com.kingdee.patchcheck.model.Patch;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.service.IpatchService;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * description: user <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁controller
 */

@RestController
@RequestMapping("/patch")
@Api(value = "测试swagger", description = "测试swagger api")
public class patchController {
    @Autowired
    private IitemService itemservice;
    @Autowired
    private IpatchService ipatchService;

    //分页获取补丁数据
    @GetMapping(value = "/getpatch/{page}/{size}/{itemid}")
    public Result<PatchVO> getpatch(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<PatchVO> getitem = ipatchService.getpatch(page, size,itemid);
        return ResultUtil.success(getitem);
    }

    //新增补丁
    @PostMapping(value = "/addpatch")
    public Result addpatch(Patch patch,@RequestParam("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(patch.toString());
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        return ResultUtil.success(ipatchService.addpatch(patch,users,itemid));


    }
    //创建补丁
    @GetMapping(value = "/ceratepatch/{patchid}")
    public Result<PatchVO> ceratepatch( @PathVariable("patchid") Integer patchid, HttpServletResponse response, HttpServletRequest request)  {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        Boolean getitem = ipatchService.createpatch(patchid,users);
        return ResultUtil.success(getitem);
    }
    //下载补丁
    @GetMapping(value = "/downloadpatch/{patchid}")
    public Result<String>   download(@PathVariable("patchid") Integer patchid,String path, HttpServletResponse response,HttpServletRequest request) throws IOException {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");

        return ResultUtil.success(ipatchService.downloadpatch(users,patchid,response));
    }
    /*//修改用户
    @PostMapping(value = "/updateitem")
    public Result updateitem(Item item, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(item.toString());
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.updateitem(item));


    }

    //删除用户
    @GetMapping(value = "/deleteitem/{id}")
    public Result<User> deleteitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.deleteitem(id));


    }
    //关闭项目
    @GetMapping(value = "/closeitem/{id}")
    public Result<User> closeitem(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(itemservice.closeitem(id));


    }
    //通过编号查找项目
    @GetMapping(value = "/getitembyid/{id}")
    public Result<User> getitembyid(@PathVariable("id")Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            return ResultUtil.success(itemservice.getitembyid(id));
        }
    }
    //通过项目名称查找项目
    @GetMapping(value = "/getitembyname")
    public Result<User> getitembyname(@RequestParam("name") String name,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getuserbyname(name));
        }
    }
    //获取用户列表
    @GetMapping(value = "/userlist")
    public Result<User> userlist(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getitemuserlist(id));
        }
    }
    //添加用户
    @PostMapping(value = "/adduser")
    public Result<Boolean> adduser(@RequestParam("user") String user, @RequestParam("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {


        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.adduser(user,itemid));
        }

    }
    //获取不在这个项目的普通用户信息
    @GetMapping(value = "/getusernoitem")
    public Result<Boolean> getusernoitem(@RequestParam("id") Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.getusernoinitem(id));
        }
    }
    //获取删除在这个项目的人员
    @GetMapping(value = "/deleteuser")
    public Result<Boolean> deleteuser(@RequestParam("itemid") Integer itemid,@RequestParam("userid") Integer userid,HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(itemservice.deleteuser(userid,itemid));
        }
    }*/

    public static Boolean checklogin(HttpServletResponse response, HttpServletRequest request) {
        User users = (User) request.getSession().getAttribute("users");
        if (StringUtils.isEmpty(users)) {
            return false;
        } else {
            return true;
        }
    }

}
