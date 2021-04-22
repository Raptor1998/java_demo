package com.hdu.xml;

import com.hdu.xml.entity.Emp;
import com.hdu.xml.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raptor
 * @description BeanTest
 * @date 2021/4/13 18:16
 */
public class BeanTest {

    @Test
    public void context(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean2.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.add();
    }
    @Test
    public void context2(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("bean6.xml");
        Emp emp = applicationContext.getBean("emp", Emp.class);
        System.out.println(emp);
    }



}
