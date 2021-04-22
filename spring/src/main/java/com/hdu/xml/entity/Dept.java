package com.hdu.xml.entity;

/**
 * @author raptor
 * @description Dept
 * @date 2021/4/13 18:21
 */
public class Dept {
    private String dname;
    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                '}';
    }
}
