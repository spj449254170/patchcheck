package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.VO.PatchVO;
import com.kingdee.patchcheck.model.*;
import com.kingdee.patchcheck.service.IitemService;
import com.kingdee.patchcheck.service.IpatchService;
import com.kingdee.patchcheck.service.IpatchtypeService;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: user <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 补丁类型controller
 */

@RestController
@RequestMapping("/patchtype")
@Api(value = "测试swagger", description = "测试swagger api")
public class patchtypeController {
   /* @Autowired
    private IitemService itemservice;
    @Autowired
    private IpatchService ipatchService;*/
    @Autowired
    private IpatchtypeService ipatchtypeService;

    /*//分页获取补丁数据
    @GetMapping(value = "/getpatch/{page}/{size}/{itemid}")
    public Result<PatchVO> getpatch(@PathVariable("page") int page, @PathVariable("size") int size, @PathVariable("itemid") Integer itemid, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<PatchVO> getitem = ipatchService.getpatch(page, size,itemid);
        return ResultUtil.success(getitem);
    }*/

    //新增补丁
    @PostMapping(value = "/addpatchtype")
    public Result addpatchtype(Patchtype patchtype, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(patchtype.toString());
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        User users = (User) request.getSession().getAttribute("users");
        return ResultUtil.success(ipatchtypeService.addpatchtype(patchtype));


    }
    //获取补丁类型列表
    @GetMapping(value = "/typelist")
    public Result<User> typelist(HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(ipatchtypeService.getpatchlist());
        }
    }
    //删除补丁类型
    @GetMapping(value = "/deletepatchtype/{id}")
    public Result<User> deletepatchtype(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(ipatchtypeService.deletepatchtype(id));


    }
    //修改补丁类型
    @PostMapping(value = "/updatepatchtype")
    public Result updatepatchtype(Patchtype patchtype, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(patchtype.toString());
        if (!checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(ipatchtypeService.updatepatchtype(patchtype));


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
