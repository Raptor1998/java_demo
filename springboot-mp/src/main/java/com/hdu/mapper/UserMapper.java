package com.hdu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hdu.entity.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author raptor
 * @description UserMapper
 * @date 2021/4/24 19:12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findByUsername(String username);

}
