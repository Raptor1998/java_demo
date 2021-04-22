package com.hdu.xml;

import com.hdu.xml.entity.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raptor
 * @description Application
 * @date 2021/4/1 14:43
 */
public class ApplicationTest {

    @Test
    public void context(){
        //加载spring配置文件
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("bean1.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        System.out.println("asdas");
        context.close();
    }
}
