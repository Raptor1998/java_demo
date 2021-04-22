package com.hdu.jdbc.service;

import com.hdu.jdbc.dao.BookDao;
import com.hdu.jdbc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author raptor
 * @description BookService
 * @date 2021/4/15 10:23
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void add(Book book) {
        bookDao.add(book);
    }

}
