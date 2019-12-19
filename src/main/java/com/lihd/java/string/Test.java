package com.lihd.java.string;

/**
 * @program: lihd-java
 * @description: String test
 * @author: li_hd
 * @create: 2019-12-18 20:49
 **/
public class Test {

    public static void main(String[] args) {

        String st1 = "abc";
        String st2 = "abc";
        System.out.println(st1 == st2);                     //true
        System.out.println(st1.equals(st2));            //true

        String st3 = "ab" + "c";
        System.out.println(st1 == st3);                     //true

        String st4 = "ab";
        String st5 = "c";
        System.out.println(st1 == st4 + st5);             //false

        String st6 = new String("abc");
        System.out.println(st6 == st1);

        String st7 = new String("123").intern();
        String st8 = "123";
        System.out.println(st7 == st8);  // true

        String st9 = new String("123");
        String st11 = st9.intern();
        String st10 = "123";
        System.out.println(st9 == st10);  // false
        System.out.println(st9 == st11); //false
        System.out.println(st10 == st11);   //true


    }


}
