package com.lihd.java.classloader;

public class Test {

    public static void main(String[] args) {

        ClassLoaderOrder classLoaderOrder = ClassLoaderOrder.getInstance();
        System.out.println(classLoaderOrder.int1);
        System.out.println(classLoaderOrder.int2);
        System.out.println(classLoaderOrder.int3);

    }


}
