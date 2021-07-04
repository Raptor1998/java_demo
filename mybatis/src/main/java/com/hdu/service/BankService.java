package com.hdu.service;

import com.hdu.entity.domain.Bank;
import org.springframework.stereotype.Service;

/**
 * @author raptor
 * @description BankService
 * @date 2021/4/24 18:15
 */
@Service
public interface BankService {

    Bank findOne(Integer id);

    Bank add(Bank bank);

    int sub(int id);
}
