package com.hdu.xml;

import com.hdu.xml.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raptor
 * @description StudentTest
 * @date 2021/4/14 10:19
 */
public class StudentTest {
    @Test
    public void context(){
        ApplicationContext applicationContext=new  ClassPathXmlApplicationContext("bean5.xml");
        User user = applicationContext.getBean("factory", User.class);
        System.out.println(user);
    }
}
