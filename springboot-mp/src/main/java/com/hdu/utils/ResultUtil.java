package com.hdu.utils;

import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.pojo.Result;

/**
 * @author raptor
 * @description ResultUtil
 * @date 2021/4/23 9:20
 */
public class ResultUtil {
    public static <T> Result<T> defineSuccess(String code, T data) {
        Result result = new Result<>();
        return result.setCode(code).setData(data);
    }

    public static <T> Result<T> success(T data) {
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS).setMsg("请求成功").setData(data);
        return result;
    }

    public static <T> Result<T> fail(String msg) {
        Result result = new Result();
        result.setCode(ResultEnum.FAIL).setMsg(msg);
        return result;
    }

    public static <T> Result<T> defineFail(String code, String msg) {
        Result result = new Result();
        result.setCode(code).setMsg(msg);
        return result;
    }

    public static <T> Result<T> define(String code, String msg, T data) {
        Result result = new Result();
        result.setCode(code).setMsg(msg).setData(data);
        return result;
    }

    public static <T> Result<T> defineFail(ResultEnum resultEnum) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        return result;
    }
}
