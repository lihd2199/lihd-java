package com.lihd.java.classloader.classloaderforresource;

import java.net.URL;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-16 20:45
 **/
public class ClassLoaderForResource {

    public static void main(String[] args) {

        URL url1 = ClassLoaderForResource.class.getClassLoader().getResource("Test.class");

        URL url2 = ClassLoaderForResource.class.getResource("/Test.class");

        System.out.println(url1);

        System.out.println(url2);


    }


}
