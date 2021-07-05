package com.hdu.service;

import com.hdu.entity.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author raptor
 * @description UserService
 * @date 2021/4/24 19:14
 */
public interface UserService extends UserDetailsService {
    User findByUsername(@Param("username") String username);
}
