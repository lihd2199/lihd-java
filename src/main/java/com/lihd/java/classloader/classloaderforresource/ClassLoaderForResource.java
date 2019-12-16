package com.lihd.java.classloader.classloaderforresource;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-16 20:45
 **/
public class ClassLoaderForResource {

    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoaderForResource.class.getClassLoader();

        classLoader.getResource("application.xml");


    }


}
