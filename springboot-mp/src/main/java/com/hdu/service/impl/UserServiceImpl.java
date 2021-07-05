package com.hdu.service.impl;

import com.hdu.entity.domain.Role;
import com.hdu.entity.domain.User;
import com.hdu.mapper.RoleMapper;
import com.hdu.mapper.UserMapper;
import com.hdu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author raptor
 * @description UserServiceImpl
 * @date 2021/4/24 19:15
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RoleMapper roleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMapper.findByUsername(s);
    }
}
