package com.lihd.java.dubbo.adaptive;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-17 22:27
 **/
public class RaceCar implements Car{


    private Wheel wheel;

    public RaceCar(Wheel wheel) {

        this.wheel = wheel;

    }
}
