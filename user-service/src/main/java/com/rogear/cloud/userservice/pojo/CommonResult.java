package com.rogear.cloud.userservice.pojo;

import java.io.Serializable;

/**
 * Created by Rogear on 2020/3/5
 **/
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -1082980142811730082L;
    private T dta;
    private String message;
    private Integer code;

    public CommonResult(T dta, String message, Integer code) {
        this.dta = dta;
        this.message = message;
        this.code = code;
    }

    public CommonResult(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public CommonResult(T data) {
        this(data,"操作成功",200);
    }

    public T getDta() {
        return dta;
    }

    public void setDta(T dta) {
        this.dta = dta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
