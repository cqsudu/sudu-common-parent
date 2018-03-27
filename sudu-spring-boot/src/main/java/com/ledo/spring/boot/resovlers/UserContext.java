package com.ledo.spring.boot.resovlers;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 用户上下文
 *
 * 仅使用在open api服务里面，内部服务杜绝使用
 *
 * Created by konghang on 2017/8/9.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserContext {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;
}
