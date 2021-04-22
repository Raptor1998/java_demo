package com.hdu.jdbc.entity;

/**
 * @author raptor
 * @description Bank
 * @date 2021/4/15 11:08
 */
public class Bank {
    private int id;
    private String username;
    private int money;

    public Bank() {
    }

    public Bank(String username, int money) {
        this.username = username;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", money=" + money +
                '}';
    }
}
