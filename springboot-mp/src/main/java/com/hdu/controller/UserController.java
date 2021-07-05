package com.hdu.controller;

import com.hdu.entity.domain.User;
import com.hdu.entity.pojo.Result;
import com.hdu.service.UserService;
import com.hdu.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author raptor
 * @description UserController
 * @date 2021/4/24 19:18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get/username", method = RequestMethod.GET)
    public Result findByUsername(@RequestParam String username) {
        System.out.println(username);
        User user = userService.findByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            return ResultUtil.success(user);
        }
        return ResultUtil.fail("用户不存在");
    }
}
