package com.hdu.service.impl;

import com.hdu.entity.domain.Bank;
import com.hdu.entity.domain.BankVo;
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
@Transactional
public class BankServiceImpl implements BankService {
    private BankMapper bankMapper;

    @Autowired
    public BankServiceImpl(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    @Override
    public Bank findOne(Integer id) {
        return bankMapper.selectByPrimaryKey(id);
    }

    @Override
    public Bank add(Bank bank) {
        bankMapper.insert(bank);
        return bank;
    }

    @Override
    public int sub(int id) {
        Bank bank = bankMapper.selectByPrimaryKey(id);
        if(bank.getMoney()>0) {
            bankMapper.sub(id);
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public BankVo findBankVoById(int id) {
        return bankMapper.findBankVoById(id);
    }
}
