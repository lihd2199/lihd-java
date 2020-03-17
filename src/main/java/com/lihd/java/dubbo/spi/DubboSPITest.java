package com.lihd.java.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-17 17:40
 **/
@Configuration
public class DubboSPITest {


    @Bean(name = "ioc")
    public Ioc ioc() {
        return new Ioc();
    }


    @Before
    public void init() {
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DubboSPITest.class);
        SpringExtensionFactory.addApplicationContext(applicationContext);
    }


    @Test
    public void sayHello() {

        ExtensionLoader<Robot> extensionLoader = ExtensionLoader.getExtensionLoader(Robot.class);

        final Robot optimusPrime = extensionLoader.getExtension("optimusPrime");

        optimusPrime.sayHello();

        final Robot bumblebee = extensionLoader.getExtension("bumblebee");

        bumblebee.sayHello();

    }


}
