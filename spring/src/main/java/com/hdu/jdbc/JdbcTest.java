package com.hdu.jdbc;

import com.hdu.jdbc.entity.Book;
import com.hdu.jdbc.service.BankService;
import com.hdu.jdbc.service.BookService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raptor
 * @description JdbcTest
 * @date 2021/4/15 10:34
 */
public class JdbcTest {



    @Test
    public void context() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.add(new Book("西游记", 20));
    }

    @Test
    public void context2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean7.xml");
        BankService bankService = context.getBean("bankService", BankService.class);
        bankService.business("lucy",10,"mike",10);
    }
}
