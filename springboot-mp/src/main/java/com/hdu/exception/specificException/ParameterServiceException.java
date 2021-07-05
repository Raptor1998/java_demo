package com.hdu.exception.specificException;

import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.exception.ServiceException;
import org.springframework.http.HttpStatus;

/**
 * @author raptor
 * @description ParameterServiceException
 * @date 2021/4/23 17:35
 */
public class ParameterServiceException extends ServiceException {
    private static final long serialVersionUID = 8362753245631601878L;

    public ParameterServiceException(ResultEnum resultEnum) {
        super(resultEnum.getCode(), resultEnum.getMsg());
        this.statusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}