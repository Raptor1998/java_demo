package com.hdu.controller;

import com.hdu.entity.domain.Bank;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.pojo.Result;
import com.hdu.exception.specificException.ParameterServiceException;
import com.hdu.service.BankService;
import com.hdu.utils.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author raptor
 * @description BookController
 * @date 2021/4/22 21:52
 */
@Api("BankController入口")
@RestController
@RequestMapping("/bank")
public class BankController {


    BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }


    @ApiOperation("获取信息")
    @ApiImplicitParam(value = "要查询的ID", name = "id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(value = "/getbank/{id}", method = RequestMethod.GET)
    public Result findOne(@PathVariable int id) {
        Bank bank = bankService.findOne(id);
        if (!ObjectUtils.isEmpty(bank)) {
            return ResultUtil.success(bank);
        }
        throw new ParameterServiceException(ResultEnum.INTERNAL_SERVER_ERROR);
    }


}
