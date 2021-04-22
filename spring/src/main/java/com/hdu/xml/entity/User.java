package com.hdu.xml.entity;

/**
 * @author raptor
 * @description User
 * @date 2021/4/1 15:03
 */
public class User {
    private String username;
    public User() {
        System.out.println("无参构造");
    }

    public User(String username) {
        System.out.println("有参构造");
        this.username = username;
    }

    public void initMethod() {
        System.out.println("执行初始化的方法");
    }

    public void destroyMethod() {
        System.out.println("执行销毁的方法");
    }

    public void setUsername(String username) {
        System.out.println("setUsername方法");
        this.username = username;
    }
}
