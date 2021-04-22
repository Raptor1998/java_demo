package com.hdu.jdbc.dao;

import com.hdu.jdbc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author raptor
 * @description BookDaoImpl
 * @date 2021/4/15 10:24
 */
@Repository
public class BookDaoImpl implements BookDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void queryAll() {
    }

    @Override
    public void add(Book book) {
        int update = jdbcTemplate.update("insert into book(name,price) values(?,?)", book.getName(), book.getPrice());
        System.out.println(update);
    }
}
