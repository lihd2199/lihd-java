package com.lihd.java.dubbo.spi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory;
import com.lihd.java.dubbo.adaptive.Car;
import com.lihd.java.dubbo.adaptive.CarMaker;
import com.lihd.java.dubbo.adaptive.RaceCarMaker;
import com.lihd.java.dubbo.adaptive.Wheel;
import com.lihd.java.dubbo.adaptive.WheelMaker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


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


    @Test
    public void makeCar(){

        ExtensionLoader<CarMaker> extensionLoader = ExtensionLoader.getExtensionLoader(CarMaker.class);

        final CarMaker raceCarMaker = extensionLoader.getExtension("RaceCarMaker");

        final Map<String, String> param = new HashMap<>();

        param.put("wheel.maker","MichelinWheelMaker");

        final URL dubbo = new URL("dubbo", "192.168.0.101", 20880, param);

        final Car car = raceCarMaker.makeCar(dubbo);

        System.out.println(car);

        ExtensionLoader<WheelMaker> wheelMakerExtensionLoader = ExtensionLoader.getExtensionLoader(WheelMaker.class);

        final WheelMaker wheelMaker = wheelMakerExtensionLoader.getAdaptiveExtension();

        final Wheel wheel = wheelMaker.makeWheel(dubbo);

        System.out.println(wheel);

    }


}
