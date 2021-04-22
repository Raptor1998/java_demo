package com.hdu.jdbc.dao;

import com.hdu.jdbc.entity.Book;

/**
 * @author raptor
 * @description BookDao
 * @date 2021/4/15 10:23
 */
public interface BookDao {
    void queryAll();
    void add(Book book);
}
