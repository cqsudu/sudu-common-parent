package com.ledo.jwt.config;

/**
 * Jwt配置
 *
 * 当前采用对称加密算法
 *
 * Created by konghang on 2017/8/8.
 */
public class JwtConfig {

    //对称加密密钥
    private String secret;

    //签名发放者
    private String issuer;

    //签名面向的用户
    private String subject;

    //签名有效期，单位毫秒
    private Long ttlMillis;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Long getTtlMillis() {
        return ttlMillis;
    }

    public void setTtlMillis(Long ttlMillis) {
        this.ttlMillis = ttlMillis;
    }
}
