package com.hdu.entity.domain;

/**
 * @author raptor
 * @description BankVo
 * @date 2021/7/4 17:40
 */
public class BankVo {
    private int id;
    private String name;

    public BankVo() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BankVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
