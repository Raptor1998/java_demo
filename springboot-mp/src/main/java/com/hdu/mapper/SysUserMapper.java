package com.hdu.mapper;

import com.hdu.entity.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author raptor
 * @description SysUserMapper
 * @date 2021/7/7 9:42
 */
@Mapper
public interface SysUserMapper {
    SysUser findByUsername(String username);
}
