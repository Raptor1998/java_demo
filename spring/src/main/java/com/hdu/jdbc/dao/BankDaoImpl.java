package com.hdu.jdbc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author raptor
 * @description BankDaoImpl
 * @date 2021/4/15 11:15
 */
@Repository
public class BankDaoImpl implements BankDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BankDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean sub(int payMoney, String username) {
        String sql = "update bank set money = money - ? where username = ?";
        return jdbcTemplate.update(sql,payMoney,username)==1;
    }

    @Override
    public Boolean add(int addMoney, String username) {
        String sql = "update bank set money = money + ? where username = ?";
        return jdbcTemplate.update(sql,addMoney,username)==1;
    }
}
