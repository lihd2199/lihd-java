package com.lihd.java.binary;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2119-12-23 11:26
 **/
public class BinaryTest {

    public static void main(String[] args) {


        System.out.println(Integer.toBinaryString(-2147483648 ));

        Integer eight = 012;

        Integer sixteen = 0x12;

        System.out.println(eight);

        System.out.println(sixteen);

        System.out.println(Integer.parseInt("11111111111111111111111111111110",2));

        System.out.println(Integer.toBinaryString(-1));


    }


}
