package com.lihd.java.designmode;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author lihd
 * @desc 单例模式 懒汉 双重检查锁定  性能受影响
 */
public class Test2 {

    /*
    volatile关键字修饰
     */
    private static volatile Test2 test2;

    private Test2(){

    }

    public static Test2 getInstance(){

        if(test2 == null){
            synchronized (Test2.class){
                if(test2 == null){
                    test2 = new Test2();
                }
            }
        }
        return test2;

    }

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class test2Class = Class.forName(Test2.class.getName());
        Constructor constructor = test2Class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Test2 test2 = (Test2) constructor.newInstance();
        System.out.println(test2);
        System.out.println(Test2.getInstance());
        System.out.println(Test2.getInstance());

    }

}
