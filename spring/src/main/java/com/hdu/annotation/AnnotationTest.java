package com.hdu.annotation;

import com.hdu.annotation.config.SpringConfig;
import com.hdu.annotation.service.PetService;
import com.hdu.xml.entity.User;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author raptor
 * @description AnnotationTest
 * @date 2021/4/14 15:42
 */
public class AnnotationTest {

    @Test
    public void context(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("bean.xml");
        PetService petService = context.getBean("petService", PetService.class);
        petService.add();
    }

    @Test
    public void context2(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        PetService petService = context.getBean("petService", PetService.class);
        petService.add();
    }
}
