package com.hdu.xml.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author raptor
 * @description Student
 * @date 2021/4/14 10:13
 */
public class Student {
    private String [] courses ;

    private List<String> list;

    private Map<String,String> map;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public String[] getCourses() {
        return courses;
    }

    public List<String> getList() {
        return list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    @Override
    public String toString() {
        return "Student{" +
                "courses=" + Arrays.toString(courses) +
                ", list=" + list +
                ", map=" + map +
                '}';
    }
}
