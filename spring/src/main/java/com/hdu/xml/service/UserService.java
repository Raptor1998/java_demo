package com.hdu.xml.service;

import com.hdu.xml.dao.UserDaoImpl;

/**
 * @author raptor
 * @description UserService
 * @date 2021/4/13 18:07
 */
public class UserService {

    private UserDaoImpl userDao;

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service add！！");
        userDao.update();
    }
}
