package com.hdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.hdu.entity.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author raptor
 * @description RoleMapper
 * @date 2021/4/24 19:14
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc "
            + "FROM role r, user_role ur "
            + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<Role> findByUid(Integer uid);
}
