package com.lihd.java.classloader.threadcontextclassloader;

/**
 * @program: lihd-java
 * @description: Thread context classloader sample
 * @author: li_hd
 * @create: 2019-12-17 11:51
 **/
public class ThreadContextLoaderSample {

    //用于线程上下文 指定某些class用特定的classloader

    //在jdbc中 加载驱动类

    public static void main(String[] args) {

        //指定 启动类加载器
        Thread.currentThread().setContextClassLoader(ThreadContextLoaderSample.class.getClassLoader());

        System.out.println(Thread.currentThread().getContextClassLoader());

        /*
         *  在BootstrapClassLoader加载的类中通过ThreadContextClassLoader加载了应用程序的实现类
         */

    }


}
