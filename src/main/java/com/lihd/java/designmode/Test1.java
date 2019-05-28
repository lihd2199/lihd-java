package com.lihd.java.designmode;

/**
 * @author lihd
 * @desc 单例模式 饿汉  占用内存
 */
public class Test1 {

    private static final Test1 test1 = new Test1();

    private Test1(){}

    public static Test1 getInstance(){
        return test1;
    }

}
