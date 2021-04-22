package com.hdu.annotation.service;

import com.hdu.annotation.dao.PetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author raptor
 * @description PetService
 * @date 2021/4/14 15:40
 */
//<bean id="petService" class="xxx">
@Service
public class PetService {

    @Autowired
    private PetDao petDao;

    public void add(){
        petDao.add();
        System.out.println("pet service add ...");
    }
}
