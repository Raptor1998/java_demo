package com.hdu.mapper;


import com.hdu.entity.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author raptor
 * @description SysRoleMapper
 * @date 2021/7/7 9:45
 */
@Mapper
public interface SysRoleMapper {
    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc "
            + "FROM sys_role r, sys_user_role ur "
            + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);
}
