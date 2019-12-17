package com.lihd.java.classloader.threadcontextclassloader;

/**
 * @program: lihd-java
 * @description: Thread context classloader sample
 * @author: li_hd
 * @create: 2019-12-17 11:51
 **/
public class ThreadContextLoaderSample {

    //用于线程上下文 指定某些class用特定的classloader  跳过双亲委派模型

    public static void main(String[] args) throws Exception{

        //指定 启动类加载器
        Thread.currentThread().setContextClassLoader(ThreadContextLoaderSample.class.getClassLoader().getParent());

        String test = ThreadContextLoaderSample.class.getResource("/").getPath().concat("Test");

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();


    }


}
