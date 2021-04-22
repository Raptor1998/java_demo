package com.hdu.aop.dao;

/**
 * @author raptor
 * @description UserDaoImpl
 * @date 2021/4/14 17:23
 */
public class UserDaoImpl implements UserDao{

    @Override
    public void say() {
        System.out.println("hello world ! ");
    }

    @Override
    public int add(int a, int b) {
        System.out.println("执行add方法！！");
        return a+b;
    }
}
