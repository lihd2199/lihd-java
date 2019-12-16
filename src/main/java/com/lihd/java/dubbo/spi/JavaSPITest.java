package com.lihd.java.dubbo.spi;

import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-13 16:32
 **/
public class JavaSPITest {


    @Test
    public void sayHello() throws Exception {

        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);

        System.out.println("Java SPI");

        serviceLoader.forEach(Robot::sayHello);

    }

}
