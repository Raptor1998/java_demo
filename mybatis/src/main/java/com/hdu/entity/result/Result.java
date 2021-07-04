package com.hdu.entity.result;

import com.hdu.entity.enumerate.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author raptor
 * @description Result
 * @date 2021/4/23 9:15
 */
@ApiModel(value = "统一返回结构")
public class Result<T> {
    @ApiModelProperty(value = "状态码",example = "A0500")
    private String code;
    @ApiModelProperty(value = "返回信息",example = "A开头摊上事了")
    private String msg;
    @ApiModelProperty(value = "所需数据",example = "null")
    private T data;

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Result setCode(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        return this;
    }
}