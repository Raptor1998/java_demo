package com.hdu.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author raptor
 * @description JwtProperties
 * @date 2021/7/6 11:02
 */
@Configuration
@ConfigurationProperties("jwt")
public class JwtProperties {

    private String secret;
    private int expiration;
    private String header;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String toString() {
        return "JwtProperties{" +
                "secret='" + secret + '\'' +
                ", expiration=" + expiration +
                ", header='" + header + '\'' +
                '}';
    }
}
