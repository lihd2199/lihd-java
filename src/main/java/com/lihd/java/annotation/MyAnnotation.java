package com.lihd.java.annotation;

import java.lang.annotation.*;

//注解可以作用的目标
@Target(value = {ElementType.TYPE,ElementType.METHOD})
//三种类型：
//RetentionPolicy.SOURCE  仅保留在源码，会被编译器丢弃。
//RetentionPolicy.CLASS  仅保留在编译后的class文件中（默认值）。
//RetentionPolicy.RUNTIME  注解保留在编译后的class文件中，并且运行时保留在JVM
@Retention(value = RetentionPolicy.RUNTIME)
//允许子类继承父类
@Inherited
//注解会被javadoc处理，javadoc默认不包括注解
@Documented
public @interface MyAnnotation {

    String test() default "default value";



}
