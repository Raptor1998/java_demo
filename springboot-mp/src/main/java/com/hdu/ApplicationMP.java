package com.hdu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author raptor
 * @description Application
 * @date 2021/4/16 13:59
 */

@MapperScan("com.hdu.mapper")
@SpringBootApplication
public class ApplicationMP {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationMP.class, args);
    }
}
