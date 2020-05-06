package com.lihd.java.lock;

import org.junit.Test;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-04-29 13:34
 **/
public class TnterruptTest {



    @Test
    public void test(){

        System.out.println(1);

        Thread.currentThread().interrupt();



    }



}
