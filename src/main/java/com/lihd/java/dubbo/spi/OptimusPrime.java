package com.lihd.java.dubbo.spi;


/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2019-12-13 16:30
 **/
public class OptimusPrime implements Robot {

    private Ioc ioc;

    public Ioc getIoc() {
        return ioc;
    }

    public void setIoc(Ioc ioc) {
        this.ioc = ioc;
    }

    @Override
    public void sayHello() {

        System.out.println(ioc.toString());

        System.out.println("Hello, I am Optimus Prime.");

    }
}
