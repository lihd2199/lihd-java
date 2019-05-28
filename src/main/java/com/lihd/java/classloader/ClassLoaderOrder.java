package com.lihd.java.classloader;

/**
 *
 */
public class ClassLoaderOrder {

    private static ClassLoaderOrder classLoaderOrder = new ClassLoaderOrder();
    static int int1;
    static int int2 = 0;
    int int3 = 1;

    private ClassLoaderOrder(){
        int1 ++;
        int2 ++;
        int3 ++;
    }

    public static ClassLoaderOrder getInstance(){
        return classLoaderOrder;
    }

    {
        System.out.println(int1);
    }


}
