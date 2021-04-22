package com.hdu.xml.entity;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author raptor
 * @description FactoryBeanTest
 * @date 2021/4/14 10:42
 */
public class FactoryBeanTest implements FactoryBean<User> {
    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public User getObject() throws Exception {
        User user=new User();
        user.setUsername("raptor");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
