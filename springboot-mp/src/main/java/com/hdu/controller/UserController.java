package com.hdu.controller;

import com.hdu.entity.domain.SysUser;
import com.hdu.entity.pojo.Result;
import com.hdu.service.UserService;
import com.hdu.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @RequestMapping(value = "/get/{username}", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public Result findByUsername(@PathVariable String username) {
        System.out.println(username);
        SysUser user = userService.findByUsername(username);
        if (!ObjectUtils.isEmpty(user)) {
            return ResultUtil.success(user);
        }
        return ResultUtil.fail("用户不存在");
    }


    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public Result admin() {
        return ResultUtil.success("admin");
    }


    @RequestMapping(value = "/student", method = RequestMethod.GET)
    @Secured("ROLE_STUDENT")
    public Result student() {
        return ResultUtil.success("student");
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public Result User() {
        return ResultUtil.success("user");
    }
}
