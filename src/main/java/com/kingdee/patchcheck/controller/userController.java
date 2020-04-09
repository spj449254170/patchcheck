package com.kingdee.patchcheck.controller;

import com.kingdee.patchcheck.VO.UserVO;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.model.User;
import com.kingdee.patchcheck.service.IuserService;
import com.kingdee.patchcheck.utils.CheckUtil;
import com.kingdee.patchcheck.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * description: user <br>
 * date: 2020\1\8 0008 12:01 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 管理员controller
 */

@RestController
@RequestMapping("/user")
@Api(value = "测试swagger", description = "测试swagger api")
public class userController {
    @Autowired
    private IuserService userservice;


    @PostMapping(value = "/login")
    @ApiOperation(value = "返回url中的参数", notes = "返回url中的参数")
    //@ApiImplicitParam(name = "id", value = "id值", paramType = "path", required = true, dataType = "Integer")
    public Result<User> login(User user, HttpServletResponse response, HttpServletRequest request) {
        //System.out.println(name+password);
        System.out.println(user.toString());
        List<User> userList = userservice.checkexists(user);
        if (userList.size()>0) {
            request.getSession().setAttribute("users", userList.get(0));
            return ResultUtil.success(userList);

        }
        return ResultUtil.NOLOGIN();
    }

    //分页获取用户数据
    @GetMapping(value = "/getuserPage")
    public Result<User> admin(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        page = page-1;
        Page<User> getuser = userservice.getuser(page, size);
        return ResultUtil.success(getuser);
    }

    //新增用户
    @PostMapping(value = "/adduser")
    public Result adduser(User user, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(user.toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(userservice.addyuser(user));


    }
    //修改用户
    @PostMapping(value = "/updateuser")
    public Result updateuser(User user, HttpServletResponse response, HttpServletRequest request) {
        System.out.println(user.toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(userservice.updateuser(user));


    }

    //删除用户
    @GetMapping(value = "/deleteuser/{id}")
    public Result<User> deleteuser(@PathVariable("id") Integer id, HttpServletResponse response, HttpServletRequest request) {
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        }
        return ResultUtil.success(userservice.deleteuser(id));


    }

    @GetMapping(value = "/getuser")
    public Result<User> getuser(HttpServletResponse response, HttpServletRequest request) {
        System.out.println(((User) request.getSession().getAttribute("users")).toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {
            return ResultUtil.success(((User) request.getSession().getAttribute("users")));
        }
    }
    @GetMapping(value = "/getuserbyid/{id}")
    public Result<User> getuserbyid(@PathVariable("id")Integer id,HttpServletResponse response, HttpServletRequest request) {
        System.out.println(((User) request.getSession().getAttribute("users")).toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(userservice.getuserbyid(id));
        }
    }
    @GetMapping(value = "/getuserbyname")
    public Result<User> getuserbyname(@RequestParam("name") String name,HttpServletResponse response, HttpServletRequest request) {
        System.out.println(((User) request.getSession().getAttribute("users")).toString());
        if (!CheckUtil.checklogin(response, request)) {
            return ResultUtil.NOLOGIN();
        } else {

            return ResultUtil.success(userservice.getuserbyname(name));
        }
    }



}
