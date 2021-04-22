package com.hdu;

import com.hdu.jdbc.service.BankService;
import com.hdu.jdbc.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author raptor
 * @description JunitTest
 * @date 2021/4/16 19:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean7.xml")
public class JunitTest {

    @Autowired
    private BankService bankService;

    @Test
    public void context(){
        bankService.business("lucy",10,"mike",10);
    }

}
