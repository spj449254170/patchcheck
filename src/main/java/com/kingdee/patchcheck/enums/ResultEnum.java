package com.kingdee.patchcheck.enums;

/**
 * description: ResultEnum <br>
 * date: 2020\1\8 0008 14:47 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 处理结果枚举
 */
public enum ResultEnum {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    NOLOGIN(-2, "未登录"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
