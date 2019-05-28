package com.lihd.java.designmode;

/**
 * @author lihd
 * @desc 内部静态类解决问题
 */
public class Test3 {

    private Test3(){

    }

    private static class Factory{
        private  final static Test3 test3 = new Test3();
    }

    public static Test3 getInstance(){
        return Factory.test3;
    }

    /*
        静态单例对象没有作为Singleton的成员变量直接实例化，因此类加载时不会实例化Singleton，
        第一次调用getInstance()时将加载内部类Factory，在该内部类中定义了一个static类型的变量instance，
        此时会首先初始化这个成员变量，由Java虚拟机来保证其线程安全性，确保该成员变量只能初始化一次。
        作者：Liuwei-Sunny
        来源：CSDN
        原文：https://blog.csdn.net/lovelion/article/details/7420888
        版权声明：本文为博主原创文章，转载请附上博文链接！
     */



}
