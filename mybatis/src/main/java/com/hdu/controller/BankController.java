package com.hdu.controller;

import com.hdu.entity.domain.Bank;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.result.Result;
import com.hdu.exception.specificException.ParameterServiceException;
import com.hdu.mapper.BankMapper;
import io.swagger.annotations.*;
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


    BankMapper bankMapper;

    @Autowired
    public BankController(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    @ApiOperation("获取信息")
    @ApiImplicitParam(value = "要查询的ID", name = "id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result findOne(@PathVariable int id) {
        Bank bank = bankMapper.selectByPrimaryKey(id);
        if (!ObjectUtils.isEmpty(bank)) {
            return null;
        }
        throw new ParameterServiceException(ResultEnum.INTERNAL_SERVER_ERROR);
    }

    //    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name",value = "姓名",required = true,paramType = "query",dataType = "String"),
//            @ApiImplicitParam(name = "age",value = "年龄",required = true,paramType = "query",dataType = "int")
//    })
//    header-->放在请求头。请求参数的获取注解：@RequestHeader
//    query -->常用于get请求的参数拼接。请求参数的获取注解：@RequestParam
//    path -->(用于restful接口)-->请求参数的获取获取注解：@PathVariable
//    body -->放在请求体。请求参数的获取注解：@RequestBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Bank add(@Valid @RequestBody Bank bank, @RequestParam String hello) {
        bankMapper.insert(bank);
        System.out.println(hello);
        return bank;
    }

}
