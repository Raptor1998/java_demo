package com.hdu.annotation.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author raptor
 * @description PetDao
 * @date 2021/4/14 16:41
 */
@Repository
public class PetDao {
    public void add(){
        System.out.println("petDao add ...");
    }
}
