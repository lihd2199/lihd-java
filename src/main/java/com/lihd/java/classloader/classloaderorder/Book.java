package com.lihd.java.classloader.classloaderorder;

public class Book {

    public static void main(String[] args) {
        staticFunction();
    }

    static Book book = new Book();

    static {
        System.out.println("书的静态代码块");
    }

    {
        System.out.println("书的普通代码块");
    }

    Book() {
        System.out.println("书的构造方法");
        System.out.println("price=" + price + ",amount=" + amount);
    }

    private static void staticFunction() {
        System.out.println("书的静态方法");
    }

    private int price = 110;

    private static int amount = 112;
}
