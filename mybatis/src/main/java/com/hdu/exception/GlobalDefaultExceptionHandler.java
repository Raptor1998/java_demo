package com.hdu.exception;

import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.result.Result;
import com.hdu.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @author raptor
 * @description GlobalDefaultExceptionHandler
 * @date 2021/4/23 17:32
 */
@RestControllerAdvice
public class GlobalDefaultExceptionHandler {

    /**
     * 处理参数异常，一般用于校验body参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationBodyException(MethodArgumentNotValidException e,HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResultUtil.defineFail(ResultEnum.Invalid_Request_Parameter);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e,HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return ResultUtil.defineFail(ResultEnum.Method_Argument_Type_Mismatch_Exception);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result handSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e,HttpServletResponse response){
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return ResultUtil.defineFail(ResultEnum.SQL_Integrity_Constraint_Violation_Exception);
    }

    /**
     * 主动throw的异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public Result handleUnProcessableServiceException(ServiceException e, HttpServletResponse response) {
        response.setStatus(e.getStatusCode().value());
        return ResultUtil.defineFail(e.getErrorCode(),e.getMessage());
//        return new ErrorMessage(e.getErrorCode(), e.getMessage());
    }

}
