package com.hdu.aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author raptor
 * @description AopConfig
 * @date 2021/4/14 18:09
 */
@Configuration
@ComponentScan(basePackages = "com.hdu.aop")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig  {
}
