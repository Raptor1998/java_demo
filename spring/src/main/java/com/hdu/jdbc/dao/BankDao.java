package com.hdu.jdbc.dao;

import com.hdu.jdbc.entity.Bank;

/**
 * @author raptor
 * @description BankDao
 * @date 2021/4/15 11:09
 */
public interface BankDao {

    Boolean sub(int payMoney,String username);

    Boolean add(int addMoney,String username);
}
