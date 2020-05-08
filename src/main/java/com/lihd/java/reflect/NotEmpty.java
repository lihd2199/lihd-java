package com.lihd.java.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-30 10:07
 **/
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NotEmpty {

    boolean notEmpty() default true;


}
