package com.kingdee.patchcheck.utils;

import com.kingdee.patchcheck.enums.ResultEnum;
import com.kingdee.patchcheck.model.Result;

/**
 * description: ResultUtil <br>
 * date: 2020\1\8 0008 14:57 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 返回结果工具类
 */
public class ResultUtil {
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result NOLOGIN() {
        Result result = new Result();
        result.setCode(ResultEnum.NOLOGIN.getCode());
        result.setMsg(ResultEnum.NOLOGIN.getMsg());
        return result;
    }
}
