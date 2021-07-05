package com.hdu.service.impl;

import com.hdu.entity.domain.Bank;
import com.hdu.mapper.BankMapper;
import com.hdu.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author raptor
 * @description BankServiceImpl
 * @date 2021/4/24 18:16
 */
@Service
public class BankServiceImpl implements BankService {
    private final BankMapper bankMapper;

    @Autowired
    public BankServiceImpl(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public Bank findOne(Integer id) {
        return bankMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Bank add(Bank bank) {
        bankMapper.insert(bank);
        return bank;
    }
}
