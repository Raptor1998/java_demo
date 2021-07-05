package com.hdu.entity.error;

import com.hdu.entity.enumerate.ExceptionEnum;

import java.io.Serializable;

/**
 * @author raptor
 * @description ErrorMessage
 * @date 2021/4/23 17:41
 */
public class ErrorMessage implements Serializable {
    private static final long serialVersionUID = 8065583911104112360L;
    private String code;
    private String msg;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ErrorMessage() {
    }

    public ErrorMessage(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ErrorMessage(ExceptionEnum exceptionEnum) {
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }
}
