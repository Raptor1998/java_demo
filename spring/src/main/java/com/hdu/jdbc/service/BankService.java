package com.hdu.jdbc.service;

import com.hdu.jdbc.dao.BankDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author raptor
 * @description BankService
 * @date 2021/4/15 11:11
 */
@Service
public class BankService {

    private BankDao bankDao;

    @Autowired
    public BankService(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    @Transactional(rollbackFor = Exception.class)
    public void business(String payName, int payMoney, String addName, int addMoney) {
        Boolean sub = bankDao.sub(payMoney, payName);
        Boolean add = bankDao.add(addMoney, addName);
        System.out.println("sub: " + sub);
        System.out.println("add: " + add);
    }

}
