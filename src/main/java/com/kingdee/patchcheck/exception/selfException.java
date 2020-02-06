package com.kingdee.patchcheck.exception;

import com.kingdee.patchcheck.enums.ResultEnum;

/**
 * description: selfException <br>
 * date: 2020\1\8 0008 14:46 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 通用异常类
 */
public class selfException extends RuntimeException {

    private Integer code;

    public selfException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
