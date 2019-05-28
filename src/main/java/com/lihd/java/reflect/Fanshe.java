package com.lihd.java.reflect;


public class Fanshe {


    public static void main(String[] args) {

        String a="abc";
        String b="a"+"bc";
        if(a == b) {
            System.out.println(true);     //输出true
        } else {
            System.out.println(false);
        }


        byte[] byteArray = a.getBytes();


    }

}
