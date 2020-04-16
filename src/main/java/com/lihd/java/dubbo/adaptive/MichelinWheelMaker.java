package com.lihd.java.dubbo.adaptive;

import com.alibaba.dubbo.common.URL;

/**
 * @program: lihd-java
 * @description:
 * @author: li_hd
 * @create: 2020-03-17 22:26
 **/
public class MichelinWheelMaker implements WheelMaker{

    @Override
    public Wheel makeWheel(URL url) {

        return new MichelinWheel();

    }


}
