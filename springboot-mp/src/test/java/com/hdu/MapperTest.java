package com.hdu;

import com.hdu.entity.domain.Role;
import com.hdu.mapper.RoleMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.List;

/**
 * @author raptor
 * @description MapperTest
 * @date 2021/4/24 19:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {


    @Autowired
    RoleMapper roleMapper;

    @Test
    public void context() {
        List<Role> roles = roleMapper.findByUid(2);
        System.out.println(roles);

    }
}
