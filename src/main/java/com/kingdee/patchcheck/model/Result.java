package com.kingdee.patchcheck.model;

/**
 * description: Result <br>
 * date: 2020\1\8 0008 14:54 <br>
 * author: Administrator <br>
 * version: 1.0 <br>
 * TODO
 */
public class Result<T> {


        /** 错误码. */
        private Integer code;

        /** 提示信息. */
        private String msg;

        /** 具体的内容. */
        private T data;

        public Integer getCode() {
        return code;
    }

        public void setCode(Integer code) {
        this.code = code;
    }

        public String getMsg() {
        return msg;
    }

        public void setMsg(String msg) {
        this.msg = msg;
    }

        public T getData() {
        return data;
    }

        public void setData(T data) {
        this.data = data;
    }

}
