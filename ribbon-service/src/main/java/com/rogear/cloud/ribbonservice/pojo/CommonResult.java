package com.rogear.cloud.ribbonservice.pojo;

import java.io.Serializable;

/**
 * Created by Rogear on 2020/3/5
 **/
public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = -1082980142811730082L;
    private T data;
    private String message;
    private Integer code;

    public CommonResult(){}

    public CommonResult(T data, String message, Integer code) {
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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
