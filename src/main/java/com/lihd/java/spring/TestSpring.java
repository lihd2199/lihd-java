package com.lihd.java.spring;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-18 19:51
 **/
public class TestSpring {

    @Before
    public void init() {
        new ClassPathXmlApplicationContext("application.xml");
    }


    @Test
    public void testApplicationListenerTest() throws InterruptedException {

        new CountDownLatch(1).await();

    }





}
