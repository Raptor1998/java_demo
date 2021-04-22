package com.hdu.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author raptor
 * @description SpringConfig
 * @date 2021/4/14 16:50
 */
@Configuration
@ComponentScan(basePackages = "com.hdu.annotation")
public class SpringConfig {
}
