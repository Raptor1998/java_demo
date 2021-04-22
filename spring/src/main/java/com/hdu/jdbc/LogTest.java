package com.hdu.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author raptor
 * @description LogTest
 * @date 2021/4/16 18:35
 */
public class LogTest {
    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        log.error("as");
        System.out.println("zxczx");
    }
}
