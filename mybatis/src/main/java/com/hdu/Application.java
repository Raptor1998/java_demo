package com.hdu;

import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author raptor
 * @description Application
 * @date 2021/4/16 13:59
 */

@MapperScan("com.hdu.mapper")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Redisson redisson() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        config.setLockWatchdogTimeout(4000);
        long lockWatchdogTimeout = config.getLockWatchdogTimeout();
        System.out.println(lockWatchdogTimeout);
        return (Redisson) Redisson.create(config);
    }
}
