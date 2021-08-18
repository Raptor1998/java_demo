package com.hdu.utils;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author raptor
 * @description RsaUtilsTest
 * @date 2021/4/25 11:06
 */
public class RsaUtilsTest {

    private String privateKeyPath = "D:\\material\\id_key_rsa";
    private String publicKeyPath = "D:\\material\\id_key_rsa.pub";

    @Test
    public void generateKey() throws Exception {
        RsaUtils.generateKey(publicKeyPath, privateKeyPath, "HDU", 2048);
    }

    @Test
    public void getPublicKey() throws Exception {
        System.out.println(RsaUtils.getPublicKey(publicKeyPath));
    }

    @Test
    public void getPrivateKey() throws Exception {
        System.out.println(RsaUtils.getPrivateKey(privateKeyPath));
    }

    @Test
    public void context() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));

    }

}