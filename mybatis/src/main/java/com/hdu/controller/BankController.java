package com.hdu.controller;

import com.hdu.entity.domain.Bank;
import com.hdu.entity.domain.BankVo;
import com.hdu.entity.enumerate.ResultEnum;
import com.hdu.entity.result.Result;
import com.hdu.exception.specificException.ParameterServiceException;
import com.hdu.service.BankService;
import com.hdu.utils.ResultUtil;
import io.swagger.annotations.*;
import org.springframework.amqp.core.Correlation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

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
    RabbitTemplate rabbitTemplate;

    @Autowired
    public BankController(BankService bankService, RabbitTemplate rabbitTemplate) {
        this.bankService = bankService;
        this.rabbitTemplate = rabbitTemplate;
    }


    @ApiOperation("获取信息")
    @ApiImplicitParam(value = "要查询的ID", name = "id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result findOne(@PathVariable int id) {
        //Bank bank = bankService.findOne(id);
        BankVo bankVoById = bankService.findBankVoById(id);
        if (!ObjectUtils.isEmpty(bankVoById)) {
            return ResultUtil.success(bankVoById);
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
        bankService.add(bank);
        System.out.println(hello);
        return bank;
    }

    @ApiOperation("减少金额")
    @ApiImplicitParam(value = "要减少的ID", name = "id", dataType = "int", paramType = "path", required = true)
    @RequestMapping(value = "/sub/{id}", method = RequestMethod.POST)
    public Result sub(@PathVariable int id) {
        int sub = bankService.sub(id);
        if (sub == 1) {
            return ResultUtil.success(null);
        } else {
            return ResultUtil.fail("困村不足");
        }
    }


    @RequestMapping(value = "/mq", method = RequestMethod.GET)
    public void publisher(@RequestParam String message,@RequestParam String ttl) {
        System.out.println(new Date() + "发送消息" + message);
        rabbitTemplate.convertAndSend("X","XC","消息来自"+ttl+"s的队列"+message, correlationData ->{
            correlationData.getMessageProperties().setExpiration(ttl);
            return correlationData;
        });
        rabbitTemplate.convertAndSend("X","XB","消息来自40s的队列"+message);
    }
}
