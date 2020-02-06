package com.kingdee.patchcheck.Handle;

import com.kingdee.patchcheck.exception.selfException;
import com.kingdee.patchcheck.model.Result;
import com.kingdee.patchcheck.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * description: ExceptionHandle <br>
 * date: 2020\1\8 0008 14:53 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * 报错监听器
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof selfException) {
            selfException girlException = (selfException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
